package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeclareSpringComponents {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		if(args.length > 0) {
			if(args[0].equals("1")) {
				context.load("classpath:META-INF/spring/app-context-annotation.xml");
			} else if(args[0].equals("2")) {
				context.load("classpath:META-INF/spring/app-context-xml2.xml");
			} else if(args[0].equals("3")) {
				context.load("classpath:META-INF/spring/app-context-annotation2.xml");
			}
		} else {
			context.load("classpath:META-INF/spring/app-context-xml.xml");
		}
		context.refresh();
		
		MessageProvider messageProvider = context.getBean("messageProvider", MessageProvider.class);
		System.out.println(messageProvider.getMessage());
	}

}
