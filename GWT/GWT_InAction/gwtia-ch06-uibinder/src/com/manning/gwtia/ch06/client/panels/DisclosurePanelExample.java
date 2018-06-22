package com.manning.gwtia.ch06.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DisclosurePanelExample extends Composite {

	private static MyUiBinder binder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, DisclosurePanelExample> {
	}

	public DisclosurePanelExample() {
		initWidget(binder.createAndBindUi(this));
	}

}
