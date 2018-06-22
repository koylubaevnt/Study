package com.apress.prospring4.ch9.l921;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxProgrammaticSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch9/l94/tx-programmatic-app-context.xml");
		context.refresh();
		
		ContactService contactService = context.getBean("contactService", ContactService.class);
		System.out.println("Contact count: " + contactService.countAll());
	}

}
