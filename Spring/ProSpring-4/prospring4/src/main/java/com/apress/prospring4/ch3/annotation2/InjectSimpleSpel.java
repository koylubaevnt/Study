package com.apress.prospring4.ch3.annotation2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service("injectSimpleSpel")
public class InjectSimpleSpel {

	@Value("#{injectSimpleConfig.name}")
	private String name;
	@Value("#{injectSimpleConfig.age}")
	private int age;
	@Value("#{injectSimpleConfig.height}")
	private float height;
	@Value("#{injectSimpleConfig.programmer}")
	private boolean programmer;
	@Value("#{injectSimpleConfig.ageInSeconds}")
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-annotation4.xml");
		context.refresh();
		
		InjectSimpleSpel simple = (InjectSimpleSpel) context.getBean("injectSimpleSpel");
		
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
