package com.apress.prospring4.ch11.l1117;

import org.springframework.context.support.GenericXmlApplicationContext;



public class TaskExecutorSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch11/app-context.xml");
		context.refresh();
		
		TaskToExecute taskToExecute = context.getBean(TaskToExecute.class);
		taskToExecute.executeTask();
		
		
	}
	
}
