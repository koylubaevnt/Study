package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.PhoneNumber;

public class PhoneNumberEditor extends Composite implements Editor<PhoneNumber>{

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, PhoneNumberEditor> {
	}

	public PhoneNumberEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	TextBox number;
	
	@UiField
	TextBox type;
	
	public void reserValues() {
		type.setText("");
		number.setText("");
	}
	
}
