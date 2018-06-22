package com.manning.gwtia.ch05.client.dataresource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DataResourceUI extends Composite {
	interface DataResourceUIUiBinder extends UiBinder<Widget, DataResourceUI> {
		
	}
	
	private static DataResourceUIUiBinder uiBinder = GWT.create(DataResourceUIUiBinder.class);
	
	public DataResourceUI() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	DataResources res;
	
	@UiField
	Button docButton;
	
	@UiHandler("docButton")
	void onClick(ClickEvent e) {
		Window.open(res.document().getSafeUri().asString(), "_blank", "");
	}
	
	
}
