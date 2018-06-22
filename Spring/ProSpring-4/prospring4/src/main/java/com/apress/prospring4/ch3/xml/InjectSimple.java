package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimple {

	private String name;
	private int age;
	private float height;
	private boolean programmer;
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml4.xml");
		context.refresh();
		
		InjectSimple simple = (InjectSimple) context.getBean("injectSimple");
		
		System.out.println(simple);
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAgeInSeconds(Long ageInSeconds) {
		this.ageInSeconds = ageInSeconds;
	}
	
	public void setProgrammer(boolean programmer) {
		this.programmer = programmer;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setName(String name) {
		this.name = name;
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
