package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Jsr330Example {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.18.xml");
		context.refresh();
		
		MessageRenderer renderer = context.getBean("messageRenderer", MessageRenderer.class);
		
		renderer.render();
	}

}
