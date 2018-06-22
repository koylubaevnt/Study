package com.apress.prospring4.ch9.l924;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxJtaSample {

	public static void main(String[] args) {
		//TODO: Не работает, выяснить почему: java.lang.IllegalStateException: Transaction no longer active 
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch9/l94/tx-jta-app-context.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("contactService", ContactService.class);
		
		Contact contact = new Contact();
		contact.setFirstName("Jta");
		contact.setLastName("Manager");
		contactService.save(contact);
		System.out.println("Contact saved successfully");
	}

}
