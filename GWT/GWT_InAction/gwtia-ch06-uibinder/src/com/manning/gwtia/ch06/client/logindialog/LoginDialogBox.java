package com.manning.gwtia.ch06.client.logindialog;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogBox extends PopupPanel {

	interface MyBinder extends UiBinder<Widget, LoginDialogBox> {
	}
	private static MyBinder binder = GWT.create(MyBinder.class);

	@UiTemplate("LoginDialogBoxAlt.ui.xml")
	interface MyAltBinder extends UiBinder<Widget, LoginDialogBox> {
	}
	private static MyAltBinder altBinder = GWT.create(MyAltBinder.class);

	@UiField
	Button btnLogin;
	@UiField
	TextBox txtEmail;
	@UiField(provided=true)
	TextBox txtPassword;
	@UiField
	Element eEmailError;
	@UiField
	SpanElement eEmailErrorText;
	@UiField
	Element ePasswordError;
	@UiField
	SpanElement ePasswordErrorText;
	@UiField
	MyStyle myStyle;
	
	interface MyStyle extends CssResource {
		String hidden();
		String borderEmpty();
		String borderOk();
		String borderError();
	}
	
	public LoginDialogBox() {
		this(false);
	}

	public LoginDialogBox(boolean useAltLayout) {
		setStyleName("");
		
		txtPassword = new PasswordTextBox();
		if(useAltLayout) {
			add(altBinder.createAndBindUi(this));
		} else {
			add(binder.createAndBindUi(this));
		}
		
		centerPopupOnPage();
		Window.addResizeHandler(repositionOnResize);
	}

	@UiFactory
	Button createLoginButton() {
		Button button = new Button();
		button.setText("Submit the login form");
		return button;
	}
	
	/* *************  EVENT HANDLERS *************** */
	@UiHandler("txtEmail")
	void emailHasFocus(FocusEvent event) {
		eEmailError.addClassName(myStyle.hidden());
		setBorderStyle(txtEmail, myStyle.borderOk());
	}
	@UiHandler("txtEmail")
	void emailHasFocus(BlurEvent event) {
		if(validateEmail()) {
			eEmailError.addClassName(myStyle.hidden());
			setBorderStyle(txtEmail, myStyle.borderOk());
		} else {
			eEmailErrorText.setInnerHTML("Email is not valid");
			eEmailError.removeClassName(myStyle.hidden());
			setBorderStyle(txtEmail, myStyle.borderError());
		}
	}
	@UiHandler("txtPassword")
	void passwordHasFocus(FocusEvent event) {
		ePasswordError.addClassName(myStyle.hidden());
		setBorderStyle(txtPassword, myStyle.borderOk());
	}
	@UiHandler("txtPassword")
	void passwordHasFocus(BlurEvent event) {
		if(validatePassword()) {
			ePasswordError.addClassName(myStyle.hidden());
			setBorderStyle(txtPassword, myStyle.borderOk());
		} else {
			ePasswordErrorText.setInnerHTML("Password is not valid");
			ePasswordError.removeClassName(myStyle.hidden());
			setBorderStyle(txtPassword, myStyle.borderError());
		}
	}
	@UiHandler("btnLogin")
	void submitLoginForm(ClickEvent event) {
		if(validateEmail() && validatePassword()) {
			Window.alert("Logging in ...");
		}
	}
	
	private void setBorderStyle(TextBox textBox, String styleName) {
		textBox.removeStyleName(myStyle.borderOk());
		textBox.removeStyleName(myStyle.borderError());
		textBox.removeStyleName(myStyle.borderEmpty());
		textBox.addStyleName(styleName);
	}
	
	private boolean validateEmail() {
		return txtEmail.getText().matches("[^\\s@]+\\@[^\\s@]+");
	}

	private boolean validatePassword() {
		return txtPassword.getText().length() >= 6;
	}
	
	private void centerPopupOnPage() {
		int minOffset = 10;
		int knownDialogWidth = 400;
		int heightAboveCenter = 200;
		
		int left = Math.max(minOffset, (Window.getClientWidth() / 2) - (knownDialogWidth / 2));
		int top = Math.max(minOffset, (Window.getClientHeight() / 2) - heightAboveCenter);
		setPopupPosition(left, top);		
	}
	
	private ResizeHandler repositionOnResize = new ResizeHandler() {
		
		@Override
		public void onResize(ResizeEvent event) {
			centerPopupOnPage();
		}
	};
}
