package com.manning.gwtia.ch06.client.bundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ClientBundleExample extends Composite {

	private static ClientBundleExampleUiBinder uiBinder = GWT.create(ClientBundleExampleUiBinder.class);

	interface ClientBundleExampleUiBinder extends UiBinder<Widget, ClientBundleExample> {
	}

	public ClientBundleExample() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiFactory
	public MyBundle creBundle() {
		MyBundle.INSTANCE.style().ensureInjected();
		return MyBundle.INSTANCE;
	}
	
}
