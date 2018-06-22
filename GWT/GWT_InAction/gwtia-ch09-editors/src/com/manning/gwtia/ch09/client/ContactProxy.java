/**
 * 
 */
package com.manning.gwtia.ch09.client;

import java.util.List;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.manning.gwtia.ch09.server.Contact;
import com.manning.gwtia.ch09.server.ContactLocator;

@ProxyFor(value = Contact.class, locator = ContactLocator.class)
public interface ContactProxy extends EntityProxy {

	Long getId();
	void setId(Long id);
	
	String getName();
	void setName(String name);
	
	String getEmail();
	void setEmail(String email);
	
	List<PhoneProxy> getPhones();
	void setPhones(List<PhoneProxy> phones);
	
	String getNote();
	void setNote(String note);
}
