package com.manning.gwtia.ch09.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.intro.IntroPanel;
import com.manning.gwtia.ch09.client.views.EmployeeView;
import com.manning.gwtia.ch09.client.views.ListAllContactsEditor;
import com.manning.gwtia.ch09.client.views.ListAllContactsView;
import com.manning.gwtia.ch09.client.widgets.PhoneBookView;
import com.manning.gwtia.ch09.client.widgets.ContactEditor;

public class CompanyView extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, CompanyView> {
	}

	interface Resources extends ClientBundle {
		@Source("gwtia.jpg")
		ImageResource logo();
	}
	
	public CompanyView() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		introPanel = new IntroPanel();
		setWidgetAsExample(introPanel);
	}
	
	private IntroPanel introPanel = null;
	
	private EmployeeView employeeView = null;
	
	private PhoneBookView phoneBookView = null;
	
	private ContactEditor contactEditor = null;
	
	private ListAllContactsView allContactsEditor = null;
	
	private ListAllContactsEditor allContactsEditor2 = null;
	
	@UiField
	Button introPanelButton;
	
	@UiField
	Button employeesButton;
	
	@UiField
	Button phoneBookButton;
	
	@UiField
	Button contactButton;
	
	@UiField
	Button listAllContactsButton;
	
	@UiField
	Button listAllContactsButton2;
	
	@UiField
	FlowPanel examplePanel;
	
	public void showIntro() {
		hideAllWidgets();
		if(introPanel == null) {
			introPanel = new IntroPanel();
			setWidgetAsExample(introPanel);
		} else {
			introPanel.setVisible(true);
		}
	}

	public void showEmployees() {
		hideAllWidgets();
		if(employeeView == null) {
			employeeView = new EmployeeView();
			setWidgetAsExample(employeeView);
		} else {
			employeeView.setVisible(true);
		}
	}

	public void showPhoneBook() {
		hideAllWidgets();
		if(phoneBookView == null) {
			phoneBookView = new PhoneBookView();
			setWidgetAsExample(phoneBookView);
		} else {
			phoneBookView.setVisible(true);
		}
	}

	public void showContacts() {
		hideAllWidgets();
		if(contactEditor == null) {
			contactEditor = new ContactEditor();
			setWidgetAsExample(contactEditor);
		} else {
			contactEditor.reset();
			contactEditor.setVisible(true);
		}
	}

	public void showLost1() {
		hideAllWidgets();
		if(allContactsEditor == null) {
			allContactsEditor = new ListAllContactsView();
			allContactsEditor.findAllContacts();
			setWidgetAsExample(allContactsEditor);
		} else {
			allContactsEditor.findAllContacts();
			allContactsEditor.setVisible(true);
		}
	}

	public void showLost2() {
		hideAllWidgets();
		if(allContactsEditor2 == null) {
			allContactsEditor2 = new ListAllContactsEditor();
			allContactsEditor2.findAllContacts();
			setWidgetAsExample(allContactsEditor2);
		} else {
			allContactsEditor2.findAllContacts();
			allContactsEditor2.setVisible(true);
		}
	}
	
	@UiHandler("introPanelButton")
	public void onClickIntroPanelButton(ClickEvent event) {
		History.newItem("");
	}

	@UiHandler("employeesButton")
	public void onClickEmployeesButton(ClickEvent event) {
		History.newItem(HistoryTokens.EMPLOYEES);
	}
	
	@UiHandler("phoneBookButton")
	public void onClickPhoneBookButton(ClickEvent event) {
		History.newItem(HistoryTokens.PHONEBOOK);
	}
	
	@UiHandler("contactButton")
	public void onClickContactButton(ClickEvent event) {
		History.newItem(HistoryTokens.CONTACTS);
	}
	
	@UiHandler("listAllContactsButton")
	public void onClickListAllContactsButton(ClickEvent event) {
		History.newItem(HistoryTokens.LIST_1);
	}
	
	@UiHandler("listAllContactsButton2")
	public void onClickListAllContactsButton2(ClickEvent event) {
		History.newItem(HistoryTokens.LIST_2);
	}
	
	private void setWidgetAsExample(Widget widget) {
		examplePanel.add(widget);
		setWidgetToMaxWidthAndHeight();
	}

	private void hideAllWidgets() {
		if(introPanel != null) {
			introPanel.setVisible(false);
		}
		if(employeeView != null) {
			employeeView.setVisible(false);
		}
		if(phoneBookView != null) {
			phoneBookView.setVisible(false);
		}
		if(contactEditor != null) {
			contactEditor.setVisible(false);
		}
		if(allContactsEditor != null) {
			allContactsEditor.setVisible(false);
		}
		if(allContactsEditor2 != null) {
			allContactsEditor2.setVisible(false);
		}
	}
	
	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
	}


}
