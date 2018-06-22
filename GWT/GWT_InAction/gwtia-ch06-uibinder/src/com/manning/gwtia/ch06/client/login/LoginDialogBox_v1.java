package com.manning.gwtia.ch06.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginDialogBox_v1 extends PopupPanel {

	private static MyBinder binder = GWT.create(MyBinder.class);

	interface MyBinder extends UiBinder<Widget, LoginDialogBox_v1> {
	}

	public LoginDialogBox_v1() {
		setStyleName("");
		add(binder.createAndBindUi(this));
	}

}
