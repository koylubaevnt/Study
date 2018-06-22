package com.apress.prospring4.ch10.l101;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConvServiceExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch10/l101/conv-service-app-context.xml");
		context.refresh();
		
		Contact contact = context.getBean("chris", Contact.class);
		System.out.println("Chris info: " + contact);
		
	}

}
