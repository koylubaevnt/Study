package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageDigestFactoryExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.10.xml");
		context.refresh();
		
		MessageDigester bean = (MessageDigester) context.getBean("digester");
		bean.digest("Hello World!");
		
	}
}
