package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring4.ch3.Oracle;

public class InjectRef {

	private Oracle oracle;
	
	public void setOracle(Oracle oracle) {
		this.oracle = oracle;
	}
	
	public static void main(String[] args) {
		
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml6.xml");
		context.refresh();
		
		InjectRef injectRef = (InjectRef) context.getBean("injectRef");
		
		System.out.println(injectRef);
	}

	@Override
	public String toString() {
		return oracle.defineMeaningOfLife();
	}
	
}
