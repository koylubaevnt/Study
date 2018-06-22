package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class InjectSimpleSpel {

	private String name;
	private int age;
	private float height;
	private boolean programmer;
	private Long ageInSeconds;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml5.xml");
		context.refresh();
		
		InjectSimpleSpel simple = (InjectSimpleSpel) context.getBean("injectSimpleSpel");
		
		System.out.println(simple);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public float getHeight() {
		return height;
	}

	public boolean isProgrammer() {
		return programmer;
	}

	public Long getAgeInSeconds() {
		return ageInSeconds;
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
