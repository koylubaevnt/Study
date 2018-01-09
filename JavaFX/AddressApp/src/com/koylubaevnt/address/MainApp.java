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
			
			// Даем контроллеру доступ к главному приложению.
			RootLayoutController controller = loader.getController();
			controller.setMainApp(this);
			
			primaryStage.show();
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		// Пытаемся загрузить последний файл с адресатами.
		File file = getPersonFilePath();
		if(file != null) {
			loadPersonalDataFromFile(file);
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
    
    /**
     * Открывает диалоговое окно для вывода статистики дней рождений.
     */
    public void showBirthdayStatistics() {
    	try {
    		// Загружаем fxml-файл и создаем новую сцену для всплывающего диалогового окна.
        	FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
    		AnchorPane page = (AnchorPane) loader.load();
    		
    		// Создаем диалоговое окно Stage.
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Birthday Statistics");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(primaryStage);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		// Передаем адресата в контроллер.
    		BirthdayStatisticsController controller = loader.getController();
    		controller.setPersonData(personData);
    		
    		// Отображаем диалоговое окно и ждем, пока пользователь его не закроет
    		dialogStage.showAndWait();
    		
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Возвращает preference файла адресатов, то есть, последний открытый файл.
     * Этот preference считывается из реестра, специфичного для конкретной
     * операционной системы. Если preference не был найден, то возвращается null.
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
     * Задаёт путь текущему загруженному файлу. Этот путь сохраняется
     * в реестре, специфичном для конкретной операционной системы.
     * 
     * @param file - файл или null, чтобы удалить путь
     */
    public void setPersonFilePath(File file) {
    	Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
    	if(file != null) {
    		prefs.put("filePath", file.getPath());
    		// Обновление заглавия сцены.
    		primaryStage.setTitle("AddressApp - " + file.getName());
    	} else {
    		prefs.remove("filePath");
    		// Обновление заглавия сцены.
    		primaryStage.setTitle("AddressApp");
    	}
    }
    
    /**
     * Загружает информацию об адресатах из указанного файла.
     * Текущая информация об адресатах будет заменена.
     * 
     * @param file
     */
    public void loadPersonalDataFromFile(File file) {
    	try {
			JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
			Unmarshaller um = context.createUnmarshaller();
			
			// Чтение XML из файла и демаршализация
			PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
			
			personData.clear();
			personData.addAll(wrapper.getPersons());
			
			// Сохраняем путь к файлу в реестре
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
     * Сохраняет текущую информацию об адресатах в указанном файле.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
    	try {
    		JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
    		Marshaller m = context.createMarshaller();
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    		
    		// Обертываем наши данные об адресатах
    		PersonListWrapper wrapper = new PersonListWrapper();
    		wrapper.setPersons(personData);
    		
    		// Маршаллируем и сохраняем XML в файл.
    		m.marshal(wrapper, file);
    		
    		// Сохраняем путь к файлу в реестре.
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
