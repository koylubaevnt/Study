package com.apress.prospring4.ch4;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DestructiveBeanWithJSR250 {

	private File file;
	private String filePath;
	
	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing Bean");
		
		if(filePath == null) {
			throw new IllegalAccessException("You must specify the filePath property of " + DestructiveBeanWithJSR250.class);
		}
		this.file = new File(filePath);
		this.file.createNewFile();
		System.out.println("File exists: " + file.exists());
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Destroing Bean");
		
		if(!file.delete()) {
			System.err.println("ERROR: failed to delete file.");
		}
		System.out.println("File exists: " + file.exists());
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.6.xml");
		context.registerShutdownHook();
		context.refresh();
		
		DestructiveBeanWithJSR250 bean = (DestructiveBeanWithJSR250) context.getBean("destructiveBean");
		//System.out.println("Calling destroy()");
		//context.destroy();
		//System.out.println("Called destroy()");
		
		
	}
}
