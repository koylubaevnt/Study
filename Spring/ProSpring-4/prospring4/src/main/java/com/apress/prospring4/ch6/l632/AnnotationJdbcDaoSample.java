package com.apress.prospring4.ch6.l632;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch6.l63.Contact;
import com.apress.prospring4.ch6.l63.ContactTelDetail;

public class AnnotationJdbcDaoSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch6/l632/app-context-xml.xml");
		context.refresh();
		
		ContactDAO contactDAO = context.getBean("contactDao", ContactDAO.class);
		
		List<Contact> contacts = contactDAO.findAll();
		
		listContacts(contacts);
		
		contacts = contactDAO.findByFirstName("Chris");
		
		listContacts(contacts);
		
		Contact contact = new Contact();
		contact.setId(1L);
		contact.setFirstName("Chris");
		contact.setLastName("John");
		contact.setBirthDate(new Date((new GregorianCalendar(1977, 10, 1)).getTime().getTime()));
		contactDAO.update(contact);
		listContacts(contactDAO.findAll());
		
		contact = new Contact();
		contact.setFirstName ("Rod");
		contact.setLastName("Johnson");
		contact.setBirthDate(new Date((new GregorianCalendar(2001, 10, 1)).getTime().getTime()));
		contactDAO.insert(contact);
		listContacts(contactDAO.findAll());
		
		
		contact = new Contact();
		contact.setFirstName("Michael");
		contact. setLastName ( "Jackson") ;
		contact.setBirthDate(new Date((new GregorianCalendar(1964, 10, 1)).getTime().getTime()));
		List<ContactTelDetail> contactTelDetails = new ArrayList<>();
		ContactTelDetail contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Home");
		contactTelDetail.setTelNumber("11111111");
		contactTelDetails.add(contactTelDetail);
		contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("MoЬile");
		contactTelDetail.setTelNumber("22222222");
		contactTelDetails.add(contactTelDetail);
		contact.setContactTelDetails(contactTelDetails);
		contactDAO.insertWithDetail(contact);
		listContacts(contactDAO.findAllWithDetail());
		
	}
	
	private static void listContacts(List<Contact> contacts) {
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
