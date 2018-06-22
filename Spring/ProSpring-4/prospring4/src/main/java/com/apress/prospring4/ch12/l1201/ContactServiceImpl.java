package com.apress.prospring4.ch12.l1201;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import org.springframework.stereotype.Service;

@Service("contactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Contact> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	@Transactional(readOnly=true)
	public List<Contact> findByFirstName(String firstName) {
		return repository.findByFirstName(firstName);
	}

	@Override
	@Transactional(readOnly=true)
	public Contact findById(Long id) {
		return repository.findOne(id);
	}

	@Override
	public Contact save(Contact contact) {
		return repository.save(contact);
	}

	@Override
	public void delete(Contact contact) {
		repository.delete(contact);

	}

}
