package com.apress.prospring4.ch8.l830;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring4.ch8.l82.Contact;
import com.google.common.collect.Lists;

@Service("springJpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly=true)
	@Override
	public List<Contact> findAll() {
		return Lists.newArrayList(contactRepository.findAll());
	}

	@Transactional(readOnly=true)
	@Override
	public List<Contact> findByFirstName(String firstName) {
		return contactRepository.findByFirstName(firstName);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Contact> findByFirstNameAndLastName(String firstName, String lastName) {
		return contactRepository.findByFirstNameAndLastName(firstName, lastName);
	}

}
