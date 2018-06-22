package com.apress.prospring4.ch10.l101;

import org.springframework.context.support.GenericXmlApplicationContext;

public class PropEditorExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/prop-editor-app-context.xml");
		context.refresh();
		
		Contact contact = context.getBean("chris", Contact.class);
		System.out.println("Chris info: " + contact);
		
		contact = context.getBean("myContact", Contact.class);
		System.out.println("My contact info: " + contact);
	}

}
