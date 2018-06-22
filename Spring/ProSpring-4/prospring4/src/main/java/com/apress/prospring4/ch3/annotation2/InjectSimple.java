package com.apress.prospring4.ch3.annotation2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimple")
public class InjectSimple {

	@Value("Chris")
	private String name;
	@Value("32")
	private int age;
	@Value("1.778")
	private float height;
	@Value("false")
	private boolean programmer;
	@Value("1009843200")
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-annotation4.xml");
		context.refresh();
		
		InjectSimple simple = (InjectSimple) context.getBean("injectSimple");
		
		System.out.println(simple);
	}
	
	@Override
	public String toString() {
		return "Name: " + name + "\n" +
				"Age: " + age + "\n" +
				"AgeInSeconds: " + ageInSeconds + "\n" +
				"Height: " + height + "\n" +
				"Is Programmer?: " + programmer;
	}

	
	
}
