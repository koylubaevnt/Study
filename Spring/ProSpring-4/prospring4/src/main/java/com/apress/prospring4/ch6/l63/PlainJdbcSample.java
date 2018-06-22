package com.apress.prospring4.ch6.l63;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class PlainJdbcSample {

	private static ContactDAO contactDAO = new PlainContactDao();
	
	public static void main(String[] args) {
		System.out.println("Listing initial contact data:");
		
		listAllContacts();
		
		System.out.println();
		System.out.println("Insert a new contact");
		Contact contact = new Contact();
		contact.setFirstName("Jacky");
		contact.setLastName("Chan");
		contact.setBirthDate(new Date(new GregorianCalendar(2001, 10, 1).getTime().getTime()));
		contactDAO.insert(contact);
		
		System.out.println("Listing contact data after new contact created:");
		listAllContacts();
		
		System.out.println();
		System.out.println("Deleting the previous created contact");
		contactDAO.delete(contact.getId());
		
		System.out.println("Listing contact data after new contact deleted:");
		listAllContacts();
	}

	private static void listAllContacts() {
		List<Contact> contacts = contactDAO.findAll();
		
		for(Contact contact : contacts) {
			System.out.println(contact);
		}
	}
}
