package com.manning.gwtia.ch09.client.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactFactory.ContactRequest;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.ContactProxy;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.views.PhoneListWidget;

public class ContactEditor extends Composite implements Editor<ContactProxy> {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, ContactEditor> {
	}

	interface Driver extends RequestFactoryEditorDriver<ContactProxy, ContactEditor> {}
	
	private ContactRequest requestContext;
	private ContactFactory requestFactory;
	private ContactProxy contactProxy;
	
	Driver driver = GWT.create(Driver.class);
	
	@Ignore
	@UiField
	LongBox fetchId;
	
	@UiField
	LongBox id;
	
	@UiField
	TextBox name;
	
	@UiField
	TextBox email;
	
	@UiField
	PhoneListWidget phonesWidget;
	
	@UiField
	TextArea note;
	
	@UiField
	Button saveContactButton;

	@UiField
	Button fetchContactButton;

	@UiField
	Button deleteContactButton;

	@UiField
	Button clearContactButton;

	
	public ContactEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		
		requestFactory = GWT.create(ContactFactory.class);
		requestContext = requestFactory.createContactRequest();
		final EventBus eventBus = new SimpleEventBus();
		requestFactory.initialize(eventBus);
		driver.initialize(requestFactory, this);
		contactProxy = requestContext.create(ContactProxy.class);
		driver.edit(contactProxy, requestContext);
		deleteContactButton.setEnabled(false);
	}

	public void reset() {
		requestContext = requestFactory.createContactRequest();
		contactProxy = requestContext.create(ContactProxy.class);
		driver.edit(contactProxy, requestContext);
		phonesWidget.clearList();
	}
	
	@UiHandler("clearContactButton")
	public void onClickClearContact(ClickEvent event) {
		reset();
		deleteContactButton.setEnabled(false);
	}
	
	@UiHandler("saveContactButton")
	public void onClickSaveContact(ClickEvent event) {
		requestContext = (ContactRequest) driver.flush();
		final ContactRequest context = requestFactory.createContactRequest();
		if(driver.hasErrors()) {
			Window.alert("Driver errors!");
		}
		requestContext.persist(contactProxy).fire(new Receiver<ContactProxy>() {
			@Override
			public void onSuccess(ContactProxy response) {
				contactProxy = context.edit(response);
				driver.edit(contactProxy, context);
				final PhoneRequest phoneRequest = requestFactory.createPhoneRequest();
				for(PhoneProxy phoneProxy : phonesWidget.getListOfPhones()) {
					PhoneProxy newProxy = phoneRequest.create(PhoneProxy.class);
					if(phoneProxy.getContactId() == null) {
						phoneProxy.setContactId(response.getId());
					}
					newProxy.setContactId(phoneProxy.getContactId());
					newProxy.setId(phoneProxy.getId());
					newProxy.setNumber(phoneProxy.getNumber());
					newProxy.setType(phoneProxy.getType());
					
					phoneRequest.persist().using(newProxy);
				}
				
				phoneRequest.fire(new Receiver<Void>() {
					
					@Override
					public void onSuccess(Void response) {
						final PhoneRequest request = requestFactory.createPhoneRequest();
						
						request.phoneList(contactProxy.getId()).fire(new Receiver<List<PhoneProxy>>() {
							
							@Override
							public void onSuccess(List<PhoneProxy> phones) {
								for(PhoneProxy p : phones) {
									if(contactProxy.getPhones() == null) {
										contactProxy.setPhones(new ArrayList<PhoneProxy>());
									}
									contactProxy.getPhones().add(p);
								}
								phonesWidget.setListOfPhones(phones);
								deleteContactButton.setEnabled(true);
							}
						});
						
					}
				});
			}
			
			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
				StringBuilder sb = new StringBuilder();
				for(ConstraintViolation<?> e : violations) {
					sb.append(e.getPropertyPath()).append(": ").append(e.getMessage());
				}
				Window.alert(sb.toString());
			}
		});
	}

	@UiHandler("fetchContactButton")
	void onClickFetch(ClickEvent event) {
		if ((fetchId.getValue()==null) || fetchId.getValue().equals("")){
			Window.alert("Select a contact number first");
			return;
		}
		
		final ContactRequest context = requestFactory.createContactRequest();

		requestContext = (ContactRequest) driver.flush();

		requestContext.find(fetchId.getValue()).fire(new Receiver<ContactProxy>() {
			@Override
			public void onSuccess(ContactProxy response) {
				if (response != null) {
					contactProxy = context.edit(response);
					driver.edit(contactProxy, context);
					deleteContactButton.setEnabled(true);
					final PhoneRequest request = requestFactory
							.createPhoneRequest();
					
					request.phoneList(contactProxy.getId()).fire(
							new Receiver<List<PhoneProxy>>() {
								public void onSuccess(
										List<PhoneProxy> phones) {

									for (PhoneProxy p : phones) {
										if (contactProxy.getPhones() == null) {
											contactProxy
													.setPhones(new ArrayList<PhoneProxy>());
										}
										contactProxy.getPhones().add(p);
									}
									phonesWidget
											.setListOfPhones(phones);
									deleteContactButton
											.setEnabled(true);

								}
							});

				} else {
					Window.alert("There is no contact with id:"
							+ fetchId.getValue());

				}

			}

			@Override
			public void onConstraintViolation(
					Set<ConstraintViolation<?>> errors) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<?> e : errors) {
					sb.append(e.getPropertyPath()).append(": ")
							.append(e.getMessage());
				}
				Window.alert(sb.toString());
			}
		});
	}

	@UiHandler("saveContactButton")
	void onClickSave(ClickEvent e) {
		requestContext = (ContactRequest) driver.flush();
		final ContactRequest context = requestFactory.createContactRequest();
		if (driver.hasErrors()) {
			Window.alert("Driver errors!");
		}
		// persist in the database
		requestContext.persist(contactProxy).fire(new Receiver<ContactProxy>() {
			@Override
			public void onSuccess(ContactProxy response) {
				// sync edited contact proxy with persisted
				contactProxy = context.edit(response);
				driver.edit(contactProxy, context);
				final PhoneRequest phoneRequest = requestFactory
						.createPhoneRequest();
				for (PhoneProxy phoneProxy : phonesWidget.getListOfPhones()) {
					PhoneProxy newProxy = phoneRequest.create(PhoneProxy.class);
					if (phoneProxy.getContactId() == null) {
						phoneProxy.setContactId(response.getId());
					}
					newProxy.setContactId(phoneProxy.getContactId());
					newProxy.setNumber(phoneProxy.getNumber());
					newProxy.setType(phoneProxy.getType());
					newProxy.setId(phoneProxy.getId());

					phoneRequest.persist().using(newProxy);

				}
				phoneRequest.fire(new Receiver<Void>() {

					@Override
					public void onSuccess(Void arg0) {
						final PhoneRequest request = requestFactory
								.createPhoneRequest();

						request.phoneList(contactProxy.getId()).fire(
								new Receiver<List<PhoneProxy>>() {
									public void onSuccess(
											List<PhoneProxy> phones) {

										for (PhoneProxy p : phones) {
											if (contactProxy.getPhones() == null) {
												contactProxy
														.setPhones(new ArrayList<PhoneProxy>());
											}
											contactProxy.getPhones().add(p);
										}
										phonesWidget.setListOfPhones(phones);
										deleteContactButton.setEnabled(true);

									}
								});
					}

				});

			}

			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> errors) {
				StringBuilder sb = new StringBuilder();
				for (ConstraintViolation<?> e : errors) {
					sb.append(e.getPropertyPath()).append(": ")
							.append(e.getMessage());
				}
				Window.alert(sb.toString());
			}
		});
	}

	@UiHandler("deleteContactButton")
	void onClickDelete(ClickEvent e) {
		final ContactRequest context = requestFactory.createContactRequest();

		requestContext = (ContactRequest) driver.flush();
		if (driver.hasErrors()) {
			Window.alert("Driver errors!");
		}

		requestContext.remove(contactProxy).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				contactProxy = context.create(ContactProxy.class);
				driver.edit(contactProxy, context);
				phonesWidget.clearList();
				deleteContactButton.setEnabled(false);
				Window.alert("Contact Deleted");
			}

		});

	}

	
}
