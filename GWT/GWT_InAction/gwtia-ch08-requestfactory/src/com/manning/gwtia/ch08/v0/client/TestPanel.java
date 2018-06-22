package com.manning.gwtia.ch08.v0.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class TestPanel extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, TestPanel> {
	}

	Logger log = Logger.getLogger("");
	@UiField
	TextBox txtInput;

	public TestPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
