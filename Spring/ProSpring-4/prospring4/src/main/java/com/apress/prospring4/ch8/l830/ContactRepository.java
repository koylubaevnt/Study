package com.apress.prospring4.ch8.l830;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.apress.prospring4.ch8.l82.Contact;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	
	List<Contact> findByFirstName(String firstName);
	
	List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
