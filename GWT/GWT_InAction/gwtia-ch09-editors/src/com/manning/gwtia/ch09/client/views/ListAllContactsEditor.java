package com.manning.gwtia.ch09.client.views;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.CompositeEditor;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactFactory.ContactRequest;
import com.manning.gwtia.ch09.client.ContactProxy;
import com.manning.gwtia.ch09.client.widgets.ContactListRowEditor;

public class ListAllContactsEditor extends Composite implements CompositeEditor<List<ContactProxy>, ContactProxy, ContactListRowEditor> {

	Logger log = Logger.getLogger("");
	
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, ListAllContactsEditor> {
	}

	interface Driver extends RequestFactoryEditorDriver<List<ContactProxy>, ListAllContactsEditor> {}
	
	private ContactRequest requestContext;
	private ContactFactory requestFactory;
	private ContactListRowEditor rowEditor;
	Driver driver = GWT.create(Driver.class);
	private CompositeEditor.EditorChain<ContactProxy, ContactListRowEditor> editorChain;
	private List<ContactListRowEditor> editorList;
	private List<ContactProxy> contactProxies;
	
	@UiField
	FlowPanel flowPanel;
	
	public ListAllContactsEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void findAllContacts() {
		flowPanel.clear();
		requestFactory = GWT.create(ContactFactory.class);
		requestContext = requestFactory.createContactRequest();
		final EventBus eventBus = new SimpleEventBus();
		requestFactory.initialize(eventBus);
		driver.initialize(eventBus, requestFactory, this);
		contactProxies = new ArrayList<ContactProxy>();
		driver.edit(contactProxies, requestContext);
		requestContext.findAllContacts().fire(new Receiver<List<ContactProxy>>() {

			@Override
			public void onSuccess(List<ContactProxy> response) {
				if(response.size() == 0) {
					Window.alert("There are no contacts to list!\nTry adding some throught the contact view");
				} else {
					for(ContactProxy c : response) {
						log.info("Contact: " + c.getId() + " = " + c.getEmail());
					}
				}
				requestContext = requestFactory.createContactRequest();
				driver.edit(response, requestContext);
			}
		});
	}

	@Override
	public void flush() {
		//No-op
	}

	@Override
	public void onPropertyChange(String... paths) {
		//No-op
	}

	@Override
	public void setValue(List<ContactProxy> value) {
		if(editorList == null) {
			editorList = new ArrayList<>();
		} else {
			for(ContactListRowEditor subEditor : editorList) {
				editorChain.detach(subEditor);
			}
			editorList.clear();
		}
		for(ContactProxy c : value) {
			ContactListRowEditor editor = new ContactListRowEditor();
			flowPanel.add(editor);
			editorList.add(editor);
			editorChain.attach(c, editor);
		}
	}

	@Override
	public void setDelegate(EditorDelegate<List<ContactProxy>> delegate) {
		//No-op
		delegate.subscribe();
	}

	@Override
	public ContactListRowEditor createEditorForTraversal() {
		rowEditor = new ContactListRowEditor();
		return rowEditor;
	}

	@Override
	public String getPathElement(ContactListRowEditor subEditor) {
		return "[" + editorList.indexOf(subEditor) + "]";
	}

	@Override
	public void setEditorChain(
			com.google.gwt.editor.client.CompositeEditor.EditorChain<ContactProxy, ContactListRowEditor> chain) {
		editorChain = chain;
		
	}

}
