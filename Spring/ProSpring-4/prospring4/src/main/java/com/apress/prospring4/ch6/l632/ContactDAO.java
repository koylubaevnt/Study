package com.apress.prospring4.ch6.l632;

import java.util.List;

import com.apress.prospring4.ch6.l63.Contact;

public interface ContactDAO {

	List<Contact> findAll();
	List<Contact> findAllWithDetail();
	List<Contact> findByFirstName(String firstName);
	String findLastNameById(Long id);
	String findFirstNameById(Long id);
	
	void insert(Contact contact);
	void insertWithDetail(Contact contact);
	void update(Contact contact);
	void delete(Long id);
	
}
