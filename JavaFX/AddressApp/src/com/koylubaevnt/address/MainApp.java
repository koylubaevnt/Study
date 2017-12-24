package com.koylubaevnt.address;
	
import java.io.IOException;

import com.koylubaevnt.address.model.Person;
import com.koylubaevnt.address.view.PersonOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
			primaryStage.show();
		} catch (IOException e) {
            e.printStackTrace();
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
}
