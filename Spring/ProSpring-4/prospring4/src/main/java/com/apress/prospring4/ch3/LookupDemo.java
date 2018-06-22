package com.apress.prospring4.ch3;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch;

public class LookupDemo {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/app-context-xml7.xml");
		context.refresh();
		
		DemoBean abstractBean = (DemoBean) context.getBean("abstractLookupBean");
		DemoBean standardBean = (DemoBean) context.getBean("standardLookupBean");
		
		displayInfo(standardBean);
		displayInfo(abstractBean);
	}

	public static void displayInfo(DemoBean bean) {
		MyHelper helper1 = bean.getMyHelper();
		MyHelper helper2 = bean.getMyHelper();
		
		System.out.println("Helper Instance the Same?: " + (helper1 == helper2));
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("lookupDemo");
		
		for(int x = 0; x < 100000; x++) {
			MyHelper helper = bean.getMyHelper();
			helper.doSomethingHelpful();
		}
		
		stopWatch.stop();
		
		System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
		
	}
	
}
