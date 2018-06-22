package com.apress.prospring4.ch8.l830;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch8.l82.Contact;

public class SpringJPASample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch8/l830/app-context-xml.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("springJpaContactService", ContactService.class);
		
		listContacts("Find all:", contactService.findAll());
		listContacts("Find by first name:", contactService.findByFirstName("Chris"));
		listContacts("Find by first name and last name:", contactService.findByFirstNameAndLastName("Chris", "Schaefer"));
		
	}

	private static void listContacts(String message, List<Contact> contacts) {
		System.out.println("");
		System.out.println(message);
		for(Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}
}
