package com.apress.prospring4.ch5.l567;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch5.l561.MyBean;

public class AopNamespaceExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch5/l567/app-context-xml.xml");
		context.refresh();
		
		MyBean myBean = (MyBean) context.getBean("myBean");
		myBean.execute();
	}

}
