package com.apress.prospring4.ch6.l650;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch6.l632.ContactDAO;

public class AnnotationJdbcDaoSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch6/l650/app-context-xml.xml");
		context.refresh();
		
		ContactDAO contactDAO = context.getBean("contactDao", ContactDAO.class);
		
		System.out.println(contactDAO.findFirstNameById(1L));
		
	}
	
}
