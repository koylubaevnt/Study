package com.apress.prospring4.ch2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldSpringDI {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
		MessageRenderer mr = context.getBean("renderer", MessageRenderer.class);
		mr.render();
	}

}