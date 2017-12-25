package com.koylubaevnt.address;
	
import java.io.IOException;

import com.koylubaevnt.address.model.Person;
import com.koylubaevnt.address.view.PersonEditDialogController;
import com.koylubaevnt.address.view.PersonOverviewController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.applet.Main;


public class MainApp extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	/**
	 * Данные, в виде наблюдаемого списка адресатов.
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
	/**
	 * Конструктор
	 */
	public MainApp() {
		// В качестве образца доюавляем некторые данные
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
	
		// Устанавливаем иконку приложения.
		this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));
		
		initRootLayout();
		
		showPersonOverview();
	}

	/**
     * Возвращает главную сцену.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
	
    /**
     * Возвращает данные в виде наблюдаемого списка адресатов.
     * @return
     */
    public ObservableList<Person> getPersonData() {
		return personData;
	}
    
	/**
     * Инициализирует корневой макет.
     */
	private void initRootLayout() {
		try {
			// Загружаем корневой макет из fxml файла.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Отображаем сцену, содержащую корневой макет.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
     * Показывает в корневом макете сведения об адресатах.
     */
    private void showPersonOverview() {
        try {
            // Загружаем сведения об адресатах.
        	FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
			AnchorPane personOverview = (AnchorPane) loader.load();
			
			// Помещаем сведения об адресатах в центр корневого макета.
			rootLayout.setCenter(personOverview);
			
			// Даем контроллеру доступ к главному приложению.
			PersonOverviewController controller = loader.getController();
			controller.setMainApp(this);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Открывает диалоговое окно для изменения деталей указанного адресата.
     * Если пользователь кликнул ОК, то изменения сохраняются в предоставленном
     * объекте адресата и возвращаеся значение true.
     * 
     * @param person - объект адресата, который надо изменить
     * @return true, если пользователь кликнул ОК, в противном случае false.
     */
    public boolean showPersonEditDialog(Person person) {
    	// Загружаем fxml-файл и создаем новую сцену
    	// для всплывающего диалогового окна.
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// Создаем диалоговое окно Stage.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Edit Person");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// Передаем адресата в контроллер.
    		PersonEditDialogController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		controller.setPerson(person);
    		
    		// Отображаем диалоговое окно и ждем, пока пользователь его не закроет
    		dialogStage.showAndWait();
    		
    		return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
    }
}
