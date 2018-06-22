package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class MethodReplacementExample {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml8.xml");
		context.refresh();
		
		ReplacementTarget replacmentTarget = (ReplacementTarget) context.getBean("replacementTarget");
		ReplacementTarget standardTarget = (ReplacementTarget) context.getBean("standardTarget");
		
		displayInfo(replacmentTarget);
		displayInfo(standardTarget);
	}

	public static void displayInfo(ReplacementTarget bean) {
		System.out.println(bean.formatMessage("Hello world!"));
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("perfTest");
		
		for(int x = 0; x < 100000; x++) {
			String out = bean.formatMessage("foo");
		}
		
		stopWatch.stop();
		System.out.println("100000 invocations took: " + stopWatch.getTotalTimeMillis() + " ms");
		
	}
	
}
