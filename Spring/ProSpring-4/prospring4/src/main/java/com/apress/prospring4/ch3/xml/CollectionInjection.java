package com.apress.prospring4.ch3.xml;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionInjection {

	private Map<String, Object> map;
	private Properties properties;
	private Set set;
	private List list;
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml-352.xml");
		context.refresh();
		
		CollectionInjection instance = (CollectionInjection) context.getBean("injectCollection");
		instance.displayInfo();
	}

	public void setList(List list) {
		this.list = list;
	}
	
	public void setSet(Set set) {
		this.set = set;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public void displayInfo() {
		System.out.println("Map contents:\n");
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " - Value: " + entry.getValue());
		}
		
		System.out.println("\nProperties contents:\n");
		for(Map.Entry entry : properties.entrySet()) {
			System.out.println("Key: " + entry.getKey() + " - Value: " + entry.getValue());
		}
		
		System.out.println("\nSet contents:\n");
		for(Object obj : set) {
			System.out.println("Value: " + obj);
		}
		
		System.out.println("\nList contents:\n");
		for(Object obj : list) {
			System.out.println("Value: " + obj);
		}
	}
}
