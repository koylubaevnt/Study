package com.apress.prospring4.ch8.l846;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch8/l846/app-context-xml.xml");
		context.refresh();
		
		ContactAuditService contactService = context.getBean("contactAuditServiceEnvers", ContactAuditService.class);
				
		System.out.println("Add new contact");
		ContactAudit contact = new ContactAudit();
		contact.setFirstName("Мichael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		contactService.save(contact);
		
		List<ContactAudit> contacts = contactService.findAll();
		listContacts(contacts);
		
		System.out.println("Update contact");
		contact.setFirstName("Tom");
		contactService.save(contact);
		contacts = contactService.findAll();
		listContacts(contacts);
		
		ContactAudit oldContact = contactService.findAuditByRevision(1L, 1);
		System.out.println();
		System.out.println("Old Contact with id 1 and rev 1: " + oldContact);
		System.out.println();
		
		oldContact = contactService.findAuditByRevision(1L, 2);
		System.out.println();
		System.out.println("Old Contact with id 1 and rev 2: " + oldContact);
		System.out.println();
		
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
