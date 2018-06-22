package com.apress.prospring4.ch8.l837;

import java.util.Date;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch8/l837/app-context-xml.xml");
		context.refresh();
		
		ContactAuditService contactService = context.getBean("contactAuditService", ContactAuditService.class);
		
		List<ContactAudit> contacts = contactService.findAll();
		listContacts(contacts);
		
		System.out.println("Add new contact");
		ContactAudit contact = new ContactAudit();
		contact.setFirstName("Мichael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		contactService.save(contact);
		contacts = contactService.findAll();
		listContacts(contacts);
		
		contact = contactService.findById(1L);
		System.out.println("");
		System.out.println("Contact with id 1:" + contact);
		System.out.println("");
		
		System.out.println("Update contact");
		contact.setFirstName("Tom");
		contactService.save(contact);
		contacts = contactService.findAll();
		listContacts(contacts);
		
	}

	private static void listContacts(List<ContactAudit> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for(ContactAudit contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}
}
