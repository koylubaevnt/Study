package com.manning.gwtia.ch09.client.views;

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
import com.manning.gwtia.ch09.client.widgets.EmployeeEditor;

public class EmployeeView extends Composite {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, EmployeeView> {
	}

	public EmployeeView() {
		employee = new Employee();
		initWidget(uiBinder.createAndBindUi(this));
		driver.initialize(employeeEditor);
		driver.edit(employee);
	}

	Employee employee;
	
	interface Driver extends SimpleBeanEditorDriver<Employee, EmployeeEditor> {}
	
	Driver driver = GWT.create(Driver.class);
	
	@UiField
	EmployeeEditor employeeEditor;
	
	@UiField
	Button resetEmployeeButton;
	
	@UiField
	Button saveEmployeeButton;
	
	@UiField
	Button fetchEmployeeButton;
	
	
	@UiHandler("resetEmployeeButton")
	public void onClickReset(ClickEvent event) {
		employeeEditor.resetValues();
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
		driver.edit(employee);
	}
	
}
