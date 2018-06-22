package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.Employee;
import com.manning.gwtia.ch09.client.domain.PhoneBook;
import com.manning.gwtia.ch09.client.domain.PhoneNumber;

public class PhoneBookView extends Composite implements PhoneBookEditor {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, PhoneBookView> {
	}

	public PhoneBookView() {
		phoneBook = new PhoneBook();
		initWidget(uiBinder.createAndBindUi(this));
		
		phoneBook.setEmployee(new Employee());
		phoneBook.setPhoneNumber(new PhoneNumber());
		driver.initialize(this);
		driver.edit(phoneBook);
	}

	PhoneBook phoneBook;
	
	interface Driver extends SimpleBeanEditorDriver<PhoneBook, PhoneBookEditor> {}
	
	Driver driver = GWT.create(Driver.class);
	
	@UiField
	EmployeeEditor employeeEditor;
	
	@UiField 
	PhoneNumberEditor phoneNumberEditor;

	@UiField
	Button resetEmployeeButton;
	
	@UiField
	Button saveEmployeeButton;
	
	@UiField
	Button fetchEmployeeButton;
	
	@Override
	public EmployeeEditor employee() {
		return employeeEditor;
	}

	@Override
	public PhoneNumberEditor phoneNumber() {
		return phoneNumberEditor;
	}
	
	@UiHandler("resetEmployeeButton")
	public void onClickReset(ClickEvent event) {
		this.employeeEditor.resetValues();
		this.phoneNumberEditor.reserValues();
	}
	
	@UiHandler("saveEmployeeButton")
	public void onClickSave(ClickEvent event) {
		driver.flush();
		if(driver.hasErrors()) {
			Window.alert("There are errors!");
		}
	}
	
	@UiHandler("fetchEmployeeButton")
	public void onClickFetch(ClickEvent event) {
		driver.edit(phoneBook);
	}
	
}
