package com.manning.gwtia.ch06.client;

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
import com.manning.gwtia.ch06.client.bundle.ClientBundleExample;
import com.manning.gwtia.ch06.client.intro.IntroPanel;
import com.manning.gwtia.ch06.client.login.LoginDialogBox_v1;
import com.manning.gwtia.ch06.client.login.LoginDialogBox_v2;
import com.manning.gwtia.ch06.client.logindialog.LoginDialogBox;
import com.manning.gwtia.ch06.client.panels.DisclosurePanelExample;
import com.manning.gwtia.ch06.client.panels.RadioButtonsExample;
import com.manning.gwtia.ch06.client.panels.SplitLayoutPanelExample;
import com.manning.gwtia.ch06.client.panels.StackPanelExample;

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

	private LoginDialogBox_v1 loginDialogBox_v1 = new LoginDialogBox_v1();
	private LoginDialogBox_v2 loginDialogBox_v2 = new LoginDialogBox_v2();
	private LoginDialogBox loginDialog = new LoginDialogBox();
	private LoginDialogBox altLoginDialog = new LoginDialogBox(true);
	
	@UiField
	Panel exampleArea;
	
	
	@UiHandler("introPanel")
	void showInstructionsPanel(ClickEvent event) {
		setWidgetAsExample(new IntroPanel());
	}
	
	@UiHandler("showLoginEx")
	void showLoginEx(ClickEvent event) {
		loginDialog.show();
	}
	@UiHandler("hideLoginEx")
	void hideLoginEx(ClickEvent event) {
		loginDialog.hide();
	}
	@UiHandler("showLoginExAlt")
	void showLoginExAlt(ClickEvent event) {
		altLoginDialog.show();
	}
	@UiHandler("hideLoginExAlt")
	void hideLoginExAlt(ClickEvent event) {
		altLoginDialog.hide();
	}
	
	@UiHandler("loginEx_v1")
	void loginEx_v1(ClickEvent event) {
		setWidgetAsExample(loginDialogBox_v1);
	}
	@UiHandler("loginEx_v2")
	void loginEx_v2(ClickEvent event) {
		setWidgetAsExample(loginDialogBox_v2);
	}
	
	@UiHandler("disclosurePanelEx")
	void showDisclosurePanelEx(ClickEvent event) {
		setWidgetAsExample(new DisclosurePanelExample());
	}
	@UiHandler("splitLayoutPanelEx")
	void showSplitLayoutPanelEx(ClickEvent event) {
		Widget widget = new SplitLayoutPanelExample();
		widget.setSize("300px", "300px");
		setWidgetAsExample(widget);
	}
	@UiHandler("stackPanelEx")
	void showStackPanelEx(ClickEvent event) {
		setWidgetAsExample(new StackPanelExample());
	}
	@UiHandler("radioButtonsEx")
	void showRadioButtonsEx(ClickEvent event) {
		setWidgetAsExample(new RadioButtonsExample());
	}
	@UiHandler("clientBundleEx")
	void showClientBundleEx(ClickEvent event) {
		setWidgetAsExample(new ClientBundleExample());
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
