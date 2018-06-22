package com.manning.gwtia.ch06.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogBox_v2 extends PopupPanel {

	private static MyBinder binder = GWT.create(MyBinder.class);

	interface MyBinder extends UiBinder<Widget, LoginDialogBox_v2> {
	}

	@UiField
	TextBox txtEmail;
	@UiField
	SpanElement eEmailErrorText;
	@UiField
	Element eEmailError;
	
	@UiField(provided=true)
	TextBox txtPassword;
	@UiField
	SpanElement ePasswordErrorText;
	@UiField
	Element ePasswordError;
	
	@UiField
	Button btnLogin;
	
	public LoginDialogBox_v2() {
		setStyleName("");
		
		txtPassword = new PasswordTextBox();
		
		add(binder.createAndBindUi(this));
	}

}
