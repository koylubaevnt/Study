package com.manning.gwtia.ch09.client.views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.widgets.PhoneEditor;

public class PhoneListWidget extends Composite {

	private static int NUMBER_OF_PHONES = 4;
	
	interface MyUiBinder extends UiBinder<Widget, PhoneListWidget> {
	}

	interface Driver extends RequestFactoryEditorDriver<List<PhoneProxy>, ListEditor<PhoneProxy, PhoneEditor>> {}
	
	@UiField
	FlowPanel container;
	
	private List<PhoneProxy> displayedList;
	private EventBus eventBus;
	private PhoneProxy proxy;
	
	private ContactFactory factory;
	private Driver driver;
	private PhoneRequest request;
	ListEditor<PhoneProxy, PhoneEditor> editor;
	
	private class PhoneEditorSource extends EditorSource<PhoneEditor> {

		@Override
		public PhoneEditor create(int index) {
			PhoneEditor editor = new PhoneEditor(factory, request, proxy);
			editor.setValue(proxy);
			container.insert(editor, index);
			return editor;
		}
		
		@Override
		public void dispose(PhoneEditor subEditor) {
			subEditor.removeFromParent();
		}
		
		@Override
		public void setIndex(PhoneEditor editor, int index) {
			container.insert(editor, index);
		}
	}
	
	private static Comparator<PhoneProxy> COMPARATOR = new Comparator<PhoneProxy>() {
		
		@Override
		public int compare(PhoneProxy o1, PhoneProxy o2) {
			if(!(o1.equals(null)) && !(o2.equals(null)))  {
				String s1 = "";
				String s2 = "";
				if(o1.getType() != null) {
					s1 = o1.getType();
				}
				if(o2.getType() != null) {
					s2 = o2.getType();
				}
				if(s1.length() == 0 && s2.length() == 0) {
					return 0;
				} else if (s1.length() == 0) {
					return 1;
				} else if (s2.length() == 0) {
					return -1;
				}
				return s1.toLowerCase().compareTo(s2.toLowerCase());
			}
			return 0;
		}
	};
	
	public PhoneListWidget() {
		
		eventBus = new SimpleEventBus();
		factory = GWT.create(ContactFactory.class);
		
		initWidget(GWT.<MyUiBinder> create(MyUiBinder.class).createAndBindUi(this));
		
		driver = GWT.create(Driver.class);
		
		editor = ListEditor.of(new PhoneEditorSource());
		driver.initialize(eventBus, factory, editor);
		request = factory.createPhoneRequest();
		driver.display(new ArrayList<PhoneProxy>());
		displayedList = editor.getList();
		
		for(int i = 0; i < NUMBER_OF_PHONES; i++) {
			proxy = request.create(PhoneProxy.class);
			displayedList.add(proxy);
		}
		Collections.sort(displayedList, COMPARATOR);
	}

	public List<PhoneProxy> getListOfPhones() {
		return displayedList;
	}
	
	public void setListOfPhones(List<PhoneProxy> list) {
		displayedList = new ArrayList<PhoneProxy>();
		Collections.sort(list, COMPARATOR);
		
		for(PhoneProxy phone : list) {
			proxy = request.create(PhoneProxy.class);
			proxy.setContactId(phone.getContactId());
			proxy.setId(phone.getId());
			proxy.setNumber(phone.getNumber());
			proxy.setType(phone.getType());
			
			displayedList.add(proxy);
		}
		driver.display(displayedList);
	}
	
	public void clearList() {
		driver.display(new ArrayList<PhoneProxy>());
		displayedList = this.editor.getList();
		
		for(int i = 0; i < NUMBER_OF_PHONES; i++) {
			proxy = request.create(PhoneProxy.class);
			displayedList.add(proxy);
		}
	}
	
	public PhoneRequest getRequest() {
		return request;
	}
	
	public void setRequest(PhoneRequest request) {
		this.request = request;
	}
}
