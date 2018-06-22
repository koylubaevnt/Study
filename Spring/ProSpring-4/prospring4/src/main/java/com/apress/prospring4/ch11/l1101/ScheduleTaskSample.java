package com.apress.prospring4.ch11.l1101;

import org.springframework.context.support.GenericXmlApplicationContext;



public class ScheduleTaskSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch11/task-namespace-app-context.xml");
		context.refresh();
		
		while (true) {
			
		}
	}
	
}
