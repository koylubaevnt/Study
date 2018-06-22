package com.apress.prospring4.ch8.l830;

import java.util.List;

import com.apress.prospring4.ch8.l82.Contact;

public interface ContactService {

	List<Contact> findAll();
	List<Contact> findByFirstName(String firstName);
	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
	
}
