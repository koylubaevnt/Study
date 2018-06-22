package com.apress.prospring4.ch4;

import java.security.MessageDigest;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AccessingFactoryBeans {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.9.xml");
		context.refresh();
		
		//MessageDigest digest = (MessageDigest) context.getBean("shaDigest");
		
		MessageDigestFactoryBean factoryBean = (MessageDigestFactoryBean) context.getBean("&shaDigest");
		
		MessageDigest shaDigest;
		try {
			shaDigest = factoryBean.getObject();
			System.out.println(shaDigest.digest("Hello world".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
