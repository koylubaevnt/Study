package com.apress.prospring4.ch8.l82;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch8/l82/app-context-xml.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("jpaContactService", ContactService.class);
		
		//Вывод списка контактов
		listContacts(contactService.findAll());
		
		//Вывод спсика контактов с детализацией
		listContactsWithDetail(contactService.findAllWithDetail());
		
		System.out.println(contactService.findById(1L));
		
		//Вывод "особой" информации
		ContactSummaryUntypeImpl contactSummaryUntype = context.getBean("contactSummaryUntype", ContactSummaryUntypeImpl.class);
		contactSummaryUntype.displayAllContactSummary();
		
		//Вывод "особой" информации исопльзую POJO объект
		ContactSummaryService contactSummaryService = context.getBean("contactSummaryService", ContactSummaryService.class);
		List<ContactSummary> contactSummaries = contactSummaryService.findAll();
		for(ContactSummary contactSummary : contactSummaries) {
			System.out.println(contactSummary);
		}
		
		// Вставка новой записи
		Contact contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home", "1111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("MoЬile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactService.save(contact);
		listContactsWithDetail(contactService.findAllWithDetail());
		
		//Обновление
		contact = contactService.findById(1L);
		System.out.println();
		System.out.println("Contact with id 1: " + contact);
		System.out.println();
		contact.setFirstName("Justin");
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for(ContactTelDetail contactTel : contactTels) {
			if(contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contactTels.remove(toDeleteContactTel);
		contactService.save(contact);
		listContactsWithDetail(contactService.findAllWithDetail());
		
		//Удаление
		contact = contactService.findById(1L);
		contactService.delete(contact);
		listContactsWithDetail(contactService.findAllWithDetail());
		
		//Простой собственный запрос
		listContacts(contactService.findAllByNativeQuery());
		
		//Собственный запрос с отображением результирующего набора SQL
		listContacts(contactService.findAllByNativeQuery2());
		
		//Использование АРl-интерфейса критериев JPA 2 для запроса с критерием
		listContactsWithDetail(contactService.findByCriteriaQuery("John", "Smith"));
	}

	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for(Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}
	
	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for(Contact contact : contacts) {
			System.out.println(contact);
			if(contact.getContactTelDetails() != null) {
				for(ContactTelDetail contactTelDetail : contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if(contact.getHobbies() != null) {
				for(Hobby hobby : contact.getHobbies()) {
					System.out.println(hobby);
				}
			}
			System.out.println();
		}
	}
}
