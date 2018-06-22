package com.apress.prospring4.ch3.annotation2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("constructorConfusion")
public class ConstructorConfusion {

	private String someValue;
	
	public ConstructorConfusion(String someValue) {
		System.out.println("ConstructorConfusion(String) called");
		this.someValue = someValue;
	}
	
	@Autowired
	public ConstructorConfusion(@Value("190") int someValue) {
		System.out.println("ConstructorConfusion(int) called");
		this.someValue = "Number: " + Integer.toString(someValue);
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-annotation3.xml");
		context.refresh();
		
		ConstructorConfusion cc = (ConstructorConfusion) context.getBean("constructorConfusion");
		
		System.out.println(cc);
	}
	
	@Override
	public String toString() {
		return someValue;
	}

}
