package com.apress.prospring4.ch9.l94;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxAnnotationSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch9/l94/tx-annotation-app-context.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("contactService", ContactService.class);
		List<Contact> contacts = contactService.findAll();
		
		for(Contact contact : contacts) {
			System.out.println(contact);
		}
		
		Contact contactSave = contactService.findById(1L);
		contactSave.setFirstName("Peter");
		contactService.save(contactSave);
		System.out.println("Contact saved successfully: " + contactSave);
		System.out.println("Contact count: " + contactService.countAll());
	}

}
