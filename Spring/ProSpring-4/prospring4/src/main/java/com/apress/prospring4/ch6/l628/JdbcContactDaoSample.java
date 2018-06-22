package com.apress.prospring4.ch6.l628;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch6.l63.Contact;
import com.apress.prospring4.ch6.l63.ContactDAO;
import com.apress.prospring4.ch6.l63.ContactTelDetail;

public class JdbcContactDaoSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch6/l628/app-context-xml.xml");
		context.refresh();
		
		ContactDAO contactDAO = context.getBean("contactDao", ContactDAO.class);
		
		List<Contact> contacts = contactDAO.findAll();
		
		for(Contact contact : contacts) {
			System.out.println(contact);
			if(contact.getContactTelDetails() != null) {
				for(ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
		}
		
		System.out.println();
	}

}
