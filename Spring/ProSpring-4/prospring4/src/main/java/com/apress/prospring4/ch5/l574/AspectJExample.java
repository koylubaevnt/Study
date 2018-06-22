package com.apress.prospring4.ch5.l574;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJExample {

	public static void main(String[] args) {
		/*Не работает. Не хватает зависимостей???*/
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch5/l574/app-context-xml.xml");
		context.refresh();
		
		MessageWriter writer = new MessageWriter();
		writer.writeMessage();
		writer.foo();		
	}

}
