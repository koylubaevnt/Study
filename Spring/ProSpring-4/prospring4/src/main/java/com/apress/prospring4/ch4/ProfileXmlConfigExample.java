package com.apress.prospring4.ch4;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProfileXmlConfigExample {
	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:META-INF/spring/ch4/*-config.xml");
		context.refresh();
		
		FoodProviderService foodProviderService = context.getBean("foodProviderService", FoodProviderService.class);
		
		List<Food> lunchSet = foodProviderService.provideLunchSet();
		
		for(Food food : lunchSet) {
			System.out.println("Food: " + food.getName());
		}
		
	}

}
