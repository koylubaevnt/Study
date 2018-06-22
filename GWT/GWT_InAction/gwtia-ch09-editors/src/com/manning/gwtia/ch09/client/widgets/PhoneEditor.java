package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorDelegate;
import com.google.gwt.editor.client.ValueAwareEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.manning.gwtia.ch09.client.ContactFactory;
import com.manning.gwtia.ch09.client.ContactFactory.PhoneRequest;
import com.manning.gwtia.ch09.client.PhoneProxy;
import com.manning.gwtia.ch09.client.event.EditPhoneEvent;

public class PhoneEditor extends Composite implements ValueAwareEditor<PhoneProxy> {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, PhoneEditor> {
	}

	interface Driver extends RequestFactoryEditorDriver<PhoneProxy, PhoneEditor> {}
	
	@Ignore
	private PhoneProxy phoneProxy;
	private Driver driver = GWT.create(Driver.class);
	
	private HandlerRegistration subscription;
	
	@UiField
	TextBox type = new TextBox();
	
	@UiField
	TextBox number = new TextBox();
	
	private PhoneRequest phoneRequest;
	private EventBus eventBus;
	
	public PhoneEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PhoneEditor(ContactFactory factory, PhoneRequest request, PhoneProxy proxy) {
		initWidget(uiBinder.createAndBindUi(this));
		eventBus = new SimpleEventBus();
		phoneRequest = request;
		phoneProxy = proxy;
		driver.initialize(eventBus, factory, this);
		driver.edit(phoneProxy, phoneRequest);
		
		ChangeHandler changeHandler = new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				driver.flush();
			}
		};
		
		FocusHandler focusHandler = new FocusHandler() {
			
			@Override
			public void onFocus(FocusEvent event) {
				eventBus.fireEvent(new EditPhoneEvent(phoneProxy, phoneRequest));
			}
		};
		
		if(eventBus != null) {
			type.addFocusHandler(focusHandler);
			type.addChangeHandler(changeHandler);
			number.addChangeHandler(changeHandler);
		}
	}



	@Override
	public void setDelegate(EditorDelegate<PhoneProxy> delegate) {
		if(subscription != null) {
			subscription.removeHandler();
		}
		subscription = delegate.subscribe();
		
		subscription = this.addEditPhoneHandler(new EditPhoneEvent.Handler() {
			
			@Override
			public void startEdit(PhoneProxy phone, RequestContext request) {
				PhoneEditor.this.onEditPhoneEvent(phone, request);
			}
		});
		
	}

	@Override
	public void flush() {
		// No-op
		
	}

	@Override
	public void onPropertyChange(String... paths) {
		// No-op
		
	}

	@Override
	public void setValue(PhoneProxy value) {
		phoneProxy = value;
	}
	
	protected void cancelSubscription() {
		if(subscription != null) {
			subscription.removeHandler();
		}
	}
	
	@Override
	protected void onLoad() {
	}
	
	@Override
	protected void onUnload() {
	}
	
	public HandlerRegistration addEditPhoneHandler(EditPhoneEvent.Handler handler) {
		return eventBus.addHandler(EditPhoneEvent.TYPE, handler);
	}

	public void onEditPhoneEvent(PhoneProxy phone, RequestContext request) {
		driver.edit(phone, request);
	}
}
