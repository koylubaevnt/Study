package com.manning.gwtia.ch08.v2.client;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;
import com.manning.gwtia.ch08.v2.client.Factory.ContactRequest;

public class TestPanel extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, TestPanel> {
	}

	Logger log = Logger.getLogger("");
	
	Factory factory;
	
	@UiField
	TextBox txtInput;

	public TestPanel() {
		factory = GWT.create(Factory.class);
		factory.initialize(new SimpleEventBus());
		
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("btnPersist")
	public void persist(ClickEvent event) {
		String rand = "" + Integer.toString((int) (Math.random() * 99999));
		
		ContactRequest context = factory.createContactRequest();
		
		PhoneProxy phone = context.create(PhoneProxy.class);
		phone.setType("Home");
		phone.setNumber("555-" + rand);
		
		ContactProxy contactProxy = context.create(ContactProxy.class);
		contactProxy.setEmail(rand + "@example.com");
		contactProxy.setName(rand);
		contactProxy.setPhones(Arrays.asList(phone));
		contactProxy.setNote("Random note for " + rand);
		
		context.persist(contactProxy).fire();
	}
	
	@UiHandler("btnPersistInvalid")
	public void persistInvalid(ClickEvent event) {
		ContactRequest context = factory.createContactRequest();
		
		ContactProxy contactProxy = context.create(ContactProxy.class);
		contactProxy.setEmail("invalid email");
		contactProxy.setName("");
		String note = "";
		for(int i = 0; i < 20; i++) {
			note += "too-long";
		}
		contactProxy.setNote(note);
		
		Receiver<Void> receiver = new Receiver<Void>() {
			
			@Override
			public void onSuccess(Void response) {
				log.info("We passed validation");
			}
			
			@Override
			public void onConstraintViolation(Set<ConstraintViolation<?>> violations) {
				for (ConstraintViolation<?> err : violations) {
					log.info(err.getPropertyPath() + ": " + err.getMessage());
				}
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				log.info("Server failure: " + error.getMessage());
			}
		};
		
		context.persist(contactProxy)
			.fire(receiver);
	}
	
	@UiHandler("btnCount")
	public void count(ClickEvent event) {
		Receiver<Integer> receiver = new Receiver<Integer>() {
			
			@Override
			public void onSuccess(Integer response) {
				log.info(response.toString());
				
			}
		}; 
		
		factory.createContactRequest()
			.count()
			.fire(receiver);
	}
	
	@UiHandler("btnList")
	public void list(ClickEvent event) {
		Receiver<List<ContactProxy>> receiver = new Receiver<List<ContactProxy>>() {
			
			@Override
			public void onSuccess(List<ContactProxy> response) {
				for(ContactProxy contact : response) {
					log.info("Contact: " + contact.getId() + " = " + contact.getEmail());
				}
			}
			
			@Override
			public void onFailure(ServerFailure error) {
				log.info("Server failure: " + error.getMessage());
			}
		};
		
		factory.createContactRequest()
			.findAllContacts()
			.fire(receiver);
	}
	
	@UiHandler("btnFetch")
	public void fetch(ClickEvent event) {
		Receiver<ContactProxy> receiver = new Receiver<ContactProxy>() {
			
			@Override
			public void onSuccess(ContactProxy response) {
				if(response != null) {
					log.info("id: " + response.getId());
					log.info("name: " + response.getName());
					log.info("email: " + response.getEmail());
					
					if(response.getPhones() != null) {
						for(PhoneProxy phone : response.getPhones()) {
							log.info("phone: " + phone.getType() + "/" + phone.getNumber());
						}
					} else {
						log.info("phone: null");
					}
					log.info("note: " + response.getNote());
				} else {
					log.info("Contact didn't exists");
				}
			}
		};
		
		factory.createContactRequest()
			.find(txtInputAsLong())
			.with("phones")//Чтобы зависимые объекты тоже подкаичвались
			.fire(receiver);
	}
	
	@UiHandler("btnUpdate")
	public void update(ClickEvent event) {
		Receiver<ContactProxy> receiver = new Receiver<ContactProxy>() {
			
			@Override
			public void onSuccess(ContactProxy response) {
				ContactRequest ctx = factory.createContactRequest();
				ContactProxy contact = ctx.edit(response);
				contact.setNote("Last updated " + new Date());
				ctx.persist(contact)
					.fire();
			}
		};
		
		factory.createContactRequest()
			.find(txtInputAsLong())
			.fire(receiver);
	}
	
	@UiHandler("btnDelete")
	public void delete(ClickEvent event) {
		Receiver<ContactProxy> receiver = new Receiver<ContactProxy>() {
			
			@Override
			public void onSuccess(ContactProxy response) {
				ContactRequest ctx = factory.createContactRequest();
				ContactProxy contact = ctx.edit(response);
				ctx.remove(contact)
					.fire();
				
			}
		};
		
		factory.createContactRequest()
			.find(txtInputAsLong())
			.fire(receiver);
	}
	
	@UiHandler("btnDeleteAll")
	public void deleteAll(ClickEvent event) {
		Receiver<List<ContactProxy>> receiver = new Receiver<List<ContactProxy>>() {
			
			@Override
			public void onSuccess(List<ContactProxy> responses) {
				ContactRequest ctx = factory.createContactRequest();
				for(ContactProxy response : responses) {
					ctx.remove(ctx.edit(response));
				}
				ctx.fire();
				
			}
		};
		
		factory.createContactRequest()
			.findAllContacts()
			.fire(receiver);
	}
	
	private Long txtInputAsLong() {
		return (long) Long.parseLong(txtInput.getText());
	}
}
