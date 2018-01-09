package com.koylubaevnt.address;
	
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.koylubaevnt.address.model.Person;
import com.koylubaevnt.address.model.PersonListWrapper;
import com.koylubaevnt.address.view.BirthdayStatisticsController;
import com.koylubaevnt.address.view.PersonEditDialogController;
import com.koylubaevnt.address.view.PersonOverviewController;
import com.koylubaevnt.address.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/**
	 * ������, � ���� ������������ ������ ���������.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
	/**
	 * �����������
	 */
	public MainApp() {
		// � �������� ������� ��������� �������� ������
		personData.add(new Person("Hans", "Muster"));
		personData.add(new Person("Ruth", "Mueller"));
		personData.add(new Person("Heinz", "Kurz"));
		personData.add(new Person("Cornelia", "Meier"));
		personData.add(new Person("Werner", "Meyer"));
		personData.add(new Person("Lydia", "Kunz"));
		personData.add(new Person("Anna", "Best"));
		personData.add(new Person("Stefan", "Meier"));
		personData.add(new Person("Stefan", "Mueller"));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
	
		// ������������� ������ ����������.
		this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
		
		initRootLayout();
		
		showPersonOverview();
	}

	/**
     * ���������� ������� �����.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
    /**
     * ���������� ������ � ���� ������������ ������ ���������.
     * @return
     */
    public ObservableList<Person> getPersonData() {
		return personData;
	}
    
	/**
     * �������������� �������� �����.
     */
	private void initRootLayout() {
		try {
			// ��������� �������� ����� �� fxml �����.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// ���������� �����, ���������� �������� �����.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			// ���� ����������� ������ � �������� ����������.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		// �������� ��������� ��������� ���� � ����������.
		File file = getPersonFilePath();
		if(file != null) {
			loadPersonalDataFromFile(file);
		}
	}
	
	/**
     * ���������� � �������� ������ �������� �� ���������.
     */
    private void showPersonOverview() {
        try {
            // ��������� �������� �� ���������.
        	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			// �������� �������� �� ��������� � ����� ��������� ������.
			rootLayout.setCenter(personOverview);
			
			// ���� ����������� ������ � �������� ����������.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ��������� ���������� ���� ��� ��������� ������� ���������� ��������.
     * ���� ������������ ������� ��, �� ��������� ����������� � ���������������
     * ������� �������� � ����������� �������� true.
     * 
     * @param person - ������ ��������, ������� ���� ��������
     * @return true, ���� ������������ ������� ��, � ��������� ������ false.
     */
    public boolean showPersonEditDialog(Person person) {
    	// ��������� fxml-���� � ������� ����� �����
    	// ��� ������������ ����������� ����.
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// ������� ���������� ���� Stage.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Edit Person");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// �������� �������� � ����������.
    		PersonEditDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		controller.setPerson(person);
    		
    		// ���������� ���������� ���� � ����, ���� ������������ ��� �� �������
    		dialogStage.showAndWait();
    		
    		return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * ��������� ���������� ���� ��� ������ ���������� ���� ��������.
     */
    public void showBirthdayStatistics() {
    	try {
    		// ��������� fxml-���� � ������� ����� ����� ��� ������������ ����������� ����.
        	FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// ������� ���������� ���� Stage.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Birthday Statistics");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// �������� �������� � ����������.
    		BirthdayStatisticsController controller = loader.getController();
    		controller.setPersonData(personData);
    		
    		// ���������� ���������� ���� � ����, ���� ������������ ��� �� �������
    		dialogStage.showAndWait();
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * ���������� preference ����� ���������, �� ����, ��������� �������� ����.
     * ���� preference ����������� �� �������, ������������ ��� ����������
     * ������������ �������. ���� preference �� ��� ������, �� ������������ null.
     * 
     * @return
     */
    public File getPersonFilePath() {
    	Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    	String filePath = prefs.get("filePath", null);
    	if(filePath != null) {
    		return new File(filePath);
    	} else {
    		return null;
    	}
    }
    
    /**
     * ����� ���� �������� ������������ �����. ���� ���� �����������
     * � �������, ����������� ��� ���������� ������������ �������.
     * 
     * @param file - ���� ��� null, ����� ������� ����
     */
    public void setPersonFilePath(File file) {
    	Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    	if(file != null) {
    		prefs.put("filePath", file.getPath());
    		// ���������� �������� �����.
    		primaryStage.setTitle("AddressApp - " + file.getName());
    	} else {
    		prefs.remove("filePath");
    		// ���������� �������� �����.
    		primaryStage.setTitle("AddressApp");
    	}
    }
    
    /**
     * ��������� ���������� �� ��������� �� ���������� �����.
     * ������� ���������� �� ��������� ����� ��������.
     * 
     * @param file
     */
    public void loadPersonalDataFromFile(File file) {
    	try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			// ������ XML �� ����� � ��������������
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
			
			personData.clear();
			personData.addAll(wrapper.getPersons());
			
			// ��������� ���� � ����� � �������
			setPersonFilePath(file);
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not load data");
			alert.setContentText("Could not load data from file:\n" + file.getPath());
			
			alert.showAndWait();
		}
    	
    }
    
    /**
     * ��������� ������� ���������� �� ��������� � ��������� �����.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
    	try {
    		JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
    		Marshaller m = context.createMarshaller();
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		// ���������� ���� ������ �� ���������
    		PersonListWrapper wrapper = new PersonListWrapper();
    		wrapper.setPersons(personData);
    		
    		// ������������ � ��������� XML � ����.
    		m.marshal(wrapper, file);
    		
    		// ��������� ���� � ����� � �������.
    		setPersonFilePath(file);
    		
    	} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Could not save data");
			alert.setContentText("Could not save data to file:\n" + file.getPath());
			
			alert.showAndWait();
		}
    }
}
