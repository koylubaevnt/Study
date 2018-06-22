package com.manning.gwtia.ch08.v2.server;

import java.util.Date;

import com.google.web.bindery.requestfactory.shared.Locator;

public class ContactLocator extends Locator<Contact, Long> {

	//Type discovery
	@Override
	public Class<Contact> getDomainType() {
		return Contact.class;
	}

	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}

	//Locator functions
	@Override
	public Contact create(Class<? extends Contact> clazz) {
		return new Contact();
	}

	@Override
	public Contact find(Class<? extends Contact> clazz, Long id) {
		return CEM.fetch(id);
	}
	
	@Override
	public Long getId(Contact contact) {
		return contact.getId();
	}

	@Override
	public Object getVersion(Contact contact) {
		return new Date(); //contact.getVersion();
	}

}
