package com.koylubaevnt.address.view;

import com.koylubaevnt.address.model.Person;
import com.koylubaevnt.address.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * ���� ��� ��������� ���������� �� ��������.
 * 
 * @author ������
 *
 */
public class PersonEditDialogController {

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField streetField;
	@FXML
	private TextField postalCodeField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField birthdayField;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked = false;
	
	/**
	 * �������������� �����-����������. ���� ����� ���������� �������������
	 * ����� ����, ��� fxml-���� ����� ��������.
	 */
	@FXML
	private void initialize() {
		
	}

	/**
	 * �������������  ����� ��� ����� ����.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	/**
	 * ������������� ��������, ���������� � ������� ����� ������.
	 * 
	 * @param person
	 */
	public void setPerson(Person person) {
		this.person = person;
		
		firstNameField.setText(person.getFirstName());
		lastNameField.setText(person.getLastName());
		streetField.setText(person.getStreet());
		postalCodeField.setText(Integer.toString(person.getPostalCode()));
		cityField.setText(person.getCity());
		birthdayField.setText(DateUtil.format(person.getBirthday()));
		birthdayField.setPromptText("dd.mm.yyyy");
	}
	
	/**
	 * 
	 * @return true, ���� ������������ ������� ��, � ������ ������ false.
	 */
	public boolean isOkClicked() {
		return okClicked;
	}
	
	/**
	 * ����������, ����� ������������ ������� �� ������ ��.
	 */
	@FXML
	public void handleOK() {
		if (isInputValid()) {
			person.setFirstName(firstNameField.getText());
			person.setLastName(lastNameField.getText());
			person.setStreet(streetField.getText());
			person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
			person.setCity(cityField.getText());
			person.setBirthday(DateUtil.parse(birthdayField.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	/**
	 * ����������, ����� ������������ ������� �� ������ Cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
	/**
	 * ��������� ���������������� ���� � ��������� �����.
	 * 
	 * @return true, ���� ���������������� ���� ���������
	 */
	private boolean isInputValid() {
		String errorMessage ="";
		if(firstNameField.getText() == null || firstNameField.getText().length() == 0) {
			errorMessage += "No valid first name!\n";
		}
		if(lastNameField.getText() == null || lastNameField.getText().length() == 0) {
			errorMessage += "No valid last name!\n";
		}
		if(streetField.getText() == null || streetField.getText().length() == 0) {
			errorMessage += "No valid street!\n";
		}
		if(postalCodeField.getText() == null || postalCodeField.getText().length() == 0) {
			errorMessage += "No valid postal code!\n";
		} else {
			// �������� ������������� �������� ��� � int.
			try {
				Integer.parseInt(postalCodeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid postal code (must be an integer)!\n";
			}
		}
		if(cityField.getText() == null || cityField.getText().length() == 0) {
			errorMessage += "No valid city!\n";
		}
		if(birthdayField.getText() == null || birthdayField.getText().length() == 0) {
			errorMessage += "No valid birthday!\n";
		} else {
			if(!DateUtil.validDate(birthdayField.getText())) {
				errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
			}
		}
		if(errorMessage.length() == 0) {
			return true;
		} else {
			// ��������� ��������� �� ������
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			
			alert.showAndWait();
			
			return false;
		}
	}
	
}
