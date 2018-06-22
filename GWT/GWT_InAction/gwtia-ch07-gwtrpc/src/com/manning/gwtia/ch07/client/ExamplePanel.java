package com.manning.gwtia.ch07.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;

public class ExamplePanel extends Composite {

	private static ExamplePanelUiBinder uiBinder = GWT.create(ExamplePanelUiBinder.class);

	interface ExamplePanelUiBinder extends UiBinder<Widget, ExamplePanel> {
	}
	
	interface Resources extends ClientBundle {
		@Source("gwtia.jpg")
		ImageResource logo();
	}
	
	public ExamplePanel() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		Window.addResizeHandler(resizeHandler);
		setWidgetAsExample(new IntroPanel());
	}

	@UiField
	Panel exampleArea;
	
	
	@UiHandler("introPanel")
	void showInstructionsPanel(ClickEvent event) {
		setWidgetAsExample(new IntroPanel());
	}

	@UiHandler("gTwitterEx")
	void showGTwitterEx(ClickEvent event) {
		setWidgetAsExample(new GTwitter());
	}
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		
		@Override
		public void onResize(ResizeEvent event) {
			setWidgetToMaxWidthAndHeight();
		}

	};

	
	private void setWidgetAsExample(Widget widget) {
		exampleArea.clear();
		exampleArea.add(widget);
	}
	
	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
	}
	
	
}
