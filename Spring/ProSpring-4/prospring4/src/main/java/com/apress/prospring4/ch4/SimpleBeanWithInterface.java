package com.apress.prospring4.ch4;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SimpleBeanWithInterface implements InitializingBean {

	private static final String DEFAULT_NAME = "Luke Skywalker";
	
	private String name;
	private int age = Integer.MIN_VALUE;
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Name: " + name + "\nAge: " + age;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.2.xml");
		context.refresh();
		
		SimpleBeanWithInterface simpleBean1 = getBean("simpleBean1", context);
		SimpleBeanWithInterface simpleBean2 = getBean("simpleBean2", context);
		SimpleBeanWithInterface simpleBean3 = getBean("simpleBean3", context);
	}

	private static SimpleBeanWithInterface getBean(String beanName, ApplicationContext context) {
		try {
			SimpleBeanWithInterface bean = (SimpleBeanWithInterface) context.getBean(beanName);
			System.out.println(bean);
			return bean;
		} catch (BeanCreationException e) {
			System.out.println("An error occured in bean configuration:" + e.getMessage());
			return null;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Initializing bean");
		if(name == null) {
			System.out.println("Using default name");
			name = DEFAULT_NAME;
		}
		if(age == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("You must set the age property of any beans of type " + SimpleBeanWithInterface.class);
		}
	}
	
}
