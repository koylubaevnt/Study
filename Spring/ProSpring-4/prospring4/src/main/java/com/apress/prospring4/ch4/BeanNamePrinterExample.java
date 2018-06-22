package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanNamePrinterExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.7.xml");
		context.refresh();
		
		BeanNamePrinter bean = (BeanNamePrinter) context.getBean("beanName");
		bean.someOperation();
		
	}
}
