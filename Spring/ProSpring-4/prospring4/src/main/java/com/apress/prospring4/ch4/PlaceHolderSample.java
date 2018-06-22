package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PlaceHolderSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.17.xml");
		context.refresh();
		
		AppProperty appProperty = context.getBean("appProperty", AppProperty.class);
		
		System.out.println("application.home: " + appProperty.getApplicationHome());
		System.out.println("user.home: " + appProperty.getUserHome());
	}
	
}
