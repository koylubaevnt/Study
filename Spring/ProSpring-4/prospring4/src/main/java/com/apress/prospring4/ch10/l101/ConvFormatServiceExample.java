package com.apress.prospring4.ch10.l101;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServiceExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/conv-format-service-app-context.xml");
		context.refresh();
		
		Contact contact = context.getBean("chris", Contact.class);
		System.out.println("Chris info: " + contact);
		
		ConversionService conversionService = context.getBean(ConversionService.class);
		
		System.out.println("Birthdate of contact is: " + conversionService.convert(contact.getBirthDate(), String.class));
		
		
		
	}

}
