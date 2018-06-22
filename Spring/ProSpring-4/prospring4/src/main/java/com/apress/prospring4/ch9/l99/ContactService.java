package com.apress.prospring4.ch9.l99;

import java.util.List;

public interface ContactService {

	List<Contact> findAll();
	Contact findById(Long id);
	Contact save(Contact contact);
	long countAll();
	
}
