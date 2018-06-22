package com.apress.prospring4.ch6.l621;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch6.l617.ContactDAO;

public class JdbcContactDaoSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch6/l621/app-context-xml.xml");
		context.refresh();
		
		ContactDAO contactDAO = context.getBean("contactDao", ContactDAO.class);
		
		System.out.println("First name for contact id 1 is: " + contactDAO.findLastNameById(1L));
	}

}
