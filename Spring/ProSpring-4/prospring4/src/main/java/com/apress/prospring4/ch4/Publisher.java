package com.apress.prospring4.ch4;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Publisher implements ApplicationContextAware {

	private ApplicationContext ctx;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.ctx = applicationContext;

	}

	public void publish(String message) {
		ctx.publishEvent(new MessageEvent(this, message));
	}
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/app-context-xml-4.14.xml");
		context.refresh();
		
		Publisher publisher = (Publisher) context.getBean("publisher");
		publisher.publish("Hello World!");
		publisher.publish("The quick brown fox jumped over the lazy dog");
	}
	
}
