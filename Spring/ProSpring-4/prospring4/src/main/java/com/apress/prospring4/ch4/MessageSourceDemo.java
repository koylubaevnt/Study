package com.apress.prospring4.ch4;

import java.util.Locale;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageSourceDemo {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.13.xml");
		context.refresh();
		
		Locale english = Locale.ENGLISH;
		Locale czech = new Locale("cs", "CZ");
		
		System.out.println(context.getMessage("msg", null, english));
		System.out.println(context.getMessage("msg", null, czech));
		System.out.println(context.getMessage("nameMsg", new Object[] {"Chris", "Schaefer"}, english));		
	}
	
}
