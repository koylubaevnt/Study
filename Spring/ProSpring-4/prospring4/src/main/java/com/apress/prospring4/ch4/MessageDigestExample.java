package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.9.xml");
		context.refresh();
		
		MessageDigester bean = (MessageDigester) context.getBean("digester");
		bean.digest("Hello World!");
		
	}
}
