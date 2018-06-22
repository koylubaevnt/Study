package com.apress.prospring4.ch11.l1113;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.GenericXmlApplicationContext;



public class AsyncTaskSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch11/async-app-context.xml");
		context.refresh();
		
		AsyncService asyncService = context.getBean("asyncService", AsyncService.class);
		
		for(int i = 0; i < 5; i++) {
			asyncService.asyncTask();
		}
		
		Future<String> result1 = asyncService.asyncWithReturn("Chris");
		Future<String> result2 = asyncService.asyncWithReturn("John");
		Future<String> result3 = asyncService.asyncWithReturn("Robert");
		
		try {
			TimeUnit.SECONDS.sleep(6);
			System.out.println("Result1: " + result1.get());
			System.out.println("Result2: " + result2.get());
			System.out.println("Result3: " + result3.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
