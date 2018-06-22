package com.manning.gwtia.ch09.client.widgets;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.manning.gwtia.ch09.client.domain.Employee;

public class EmployeeEditor extends Composite implements Editor<Employee> {

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

	interface MyUiBinder extends UiBinder<Widget, EmployeeEditor> {
	}

	public EmployeeEditor() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	TextBox name;
	
	@UiField
	@Path(value = "title")
	TextBox employeeTitle;
	
	@Ignore
	Label id;
	
	public void resetValues() {
		name.setText("");
		employeeTitle.setText("");
	}
}
