package com.apress.prospring4.ch10.l101;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvAnotherServiceExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/conv-another-service-app-context.xml");
		context.refresh();
		
		Contact contact = context.getBean("chris", Contact.class);
		System.out.println("Chris info: " + contact);
		
		ConversionService conversionService = context.getBean(ConversionService.class);
		
		AnotherContact anotherContact = conversionService.convert(contact, AnotherContact.class);
		System.out.println("Another contact info: " + anotherContact);
		
		String[] stringArray = conversionService.convert("a,b,c", String[].class);
		System.out.println("String array: " + stringArray[0] + stringArray[1] + stringArray[2]);
		
		List<String> listString = new ArrayList<>();
		listString.add("a");
		listString.add("b");
		listString.add("c");
		
		Set<String> setString = conversionService.convert(listString, Set.class);
		
		for(String string: setString) {
			System.out.println("Set: " + string);
		}
		
	}

}
