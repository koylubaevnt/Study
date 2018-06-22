package com.apress.prospring4.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBean {

	private String name;
	private int age;
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Name: " + name +"\n" + "Age: " + age;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml10.xml");
		context.refresh();
		
		SimpleBean parent = (SimpleBean) context.getBean("inheritParent");
		SimpleBean child = (SimpleBean) context.getBean("inheritChild");
		
		System.out.println("Parent:\n" + parent);
		System.out.println("Child:\n" + child);
	}
	
}
