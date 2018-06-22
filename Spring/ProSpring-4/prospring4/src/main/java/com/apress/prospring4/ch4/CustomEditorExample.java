package com.apress.prospring4.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomEditorExample {

	private Name name;
	
	public Name getName() {
		return name;
	}
	
	public void setName(Name name) {
		this.name = name;
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.12.xml");
		context.refresh();
		
		CustomEditorExample bean = (CustomEditorExample) context.getBean("exampleBean");
		System.out.println(bean.getName());
	}

}
