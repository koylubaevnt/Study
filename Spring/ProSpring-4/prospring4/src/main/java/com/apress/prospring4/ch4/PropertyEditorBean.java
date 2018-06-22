package com.apress.prospring4.ch4;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Pattern;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.GenericXmlApplicationContext;

public class PropertyEditorBean {
	private byte[] bytes;				//ByteArrayPropertyEditor
	private Class 	cls;					//ClassEditor
	private Boolean trueOrFalse;		//CustomBooleanEditor
	private List<String> stringList;	//CustomCollectionEditor
	private Date date;					//CustomDateEditor
	private Float floatValue;			//CustomNumЬerEditor
	private File file;					//FileEditor
	private InputStream stream;			//InputStreamEditor
	private Locale locale;				//LocaleEditor
	private Pattern pattern;			//PatternEditor
	private Properties properties;		//PropertiesEditor
	private String trimString;			//StringTrimmerEditor
	private URL url;					//URLEditor
	
	public void setCls(Class cls) {
		// Установка класса
		System.out.println("Setting class: " + cls.getName());
		this.cls = cls;
	}
	public void setFile(File file) {
		// Установка файла
		System.out.println("Setting file: " + file.getName());
		this.file = file;
	}
	public void setLocale(Locale locale) {
		// Установка локали
		System.out.println( "Setting locale: " + locale.getDisplayName());
		this.locale = locale;
	}
	public void setProperties(Properties properties) {
		// Вывод количества загруженных свойств
		System.out.println("Loaded " + properties.size() + " properties");
		this.properties = properties;
	}
	public void setUrl(URL url) {
		// Установка URL
		System.out.println("Setting URL: " + url.toExternalForm());
		this.url = url;
	}
	public void setBytes(byte [] bytes) {
		// Вывод количества добавленных байтов
		System.out.println("Adding " + bytes.length + " bytes");
		this.bytes = bytes;
	}
	public void setTrueOrFalse(Boolean trueOrFalse) {
		// Установка булевского значения
		System.out.println("Setting Boolean: " + trueOrFalse);
		this.trueOrFalse = trueOrFalse;
	}
	public void setStringList(List<String> stringList) {
		// Установка списка строк
		System.out.println("Setting string list with size: "
				+ stringList.size());
		this.stringList = stringList;
		for (String string: stringList)
			// Член типа String
			System.out.println("String memЬer: " + string);
	}
	public void setDate(Date date) {
		// Установка даты
		System.out.println("Setting date: " + date);
		this.date = date;
	}
	public void setFloatValue(Float floatValue) {
		// Установка значения с плавающей точкой
		System.out.println("Setting float value: " + floatValue);
		this.floatValue = floatValue;
	}
	public void setStream(InputStream stream) {
		// Установка потока
		System.out.println("Setting stream: " + stream);
		this.stream = stream;
	}
	public void setPattern(Pattern pattern) {
		// Установка шаблона
		System.out.println("Setting pattern: " + pattern);
		this.pattern = pattern;
	}
	public void setTrimString(String trimString) {
		// Установка усеченной строки
		System.out.println("Setting trim string: " + trimString);
		this.trimString = trimString;
	}
	public static class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
		@Override
		public void registerCustomEditors(PropertyEditorRegistry registry) {
			SimpleDateFormat dateFormatter = new SimpleDateFormat ("MM/dd/yyyy");
			registry.registerCustomEditor(Date.class, new CustomDateEditor(dateFormatter, true));
			registry.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		}
		
		public static void main(String[] args) throws Exception {
			//File file = new File("test.txt");
			//file.createNewFile();
			File file = File.createTempFile("test", "txt");
			file.deleteOnExit();
			GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
			ctx.load("classpath:META-INF/spring/ch4/app-context-xml-4.11.xml");
			ctx.refresh();
			PropertyEditorBean bean = (PropertyEditorBean) ctx.getBean("builtInSample");
			
		}
	}
}