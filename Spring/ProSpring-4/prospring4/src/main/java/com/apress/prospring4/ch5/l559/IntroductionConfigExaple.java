package com.apress.prospring4.ch5.l559;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch5.l549.IsModified;
import com.apress.prospring4.ch5.l549.TargetBean;

public class IntroductionConfigExaple {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/ch5/l559/app-context-xml.xml");
		ctx.refresh();
		
		TargetBean proxy = (TargetBean) ctx.getBean("bean");
		
		IsModified proxyInterface = (IsModified) proxy;
		
		System.out.println("Is TargetBean?: " + (proxy instanceof TargetBean));
		System.out.println("Is IsModified?: " + (proxy instanceof IsModified));
	
		System.out.println("has been modified?: " + proxyInterface.isModified());
		
		proxy.setName("Chris");
		System.out.println("has been modified?: " + proxyInterface.isModified());
		proxy.setName("Chris Schaefer");
		System.out.println("has been modified?: " + proxyInterface.isModified());
		
	}

}
