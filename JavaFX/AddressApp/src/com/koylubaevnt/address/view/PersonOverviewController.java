package com.koylubaevnt.address.view;

import com.koylubaevnt.address.MainApp;
import com.koylubaevnt.address.model.Person;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {

	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;
	
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;
	
	// ������ �� ������� ����������
	private MainApp mainApp;
	
	/**
	 * �����������.
	 * ����������� ���������� ������ ������ initialize()
	 */
	public PersonOverviewController() {
	}
	
	/**
	 * ������������� ������-�����������. ���� ����� ���������� �������������
	 * ����� ����, ��� fxml-���� ����� ��������.
	 */
	@FXML
	private void initialize() {
		// ������������� ������� ��������� � ����� ���������.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
	}

	/**
	 * ���������� ������� �����������, ������� ���� �� ���� ������.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		
		// ���������� � ������� ������ �� ������������ ������
		personTable.setItems(mainApp.getPersonData());
	}
	
	
}
