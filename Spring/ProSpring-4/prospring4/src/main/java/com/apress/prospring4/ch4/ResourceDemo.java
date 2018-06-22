package com.apress.prospring4.ch4;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceDemo {

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext();
		
		File file = File.createTempFile("file", "txt");
		file.deleteOnExit();
		
		System.out.println(file.getPath());
		
		Resource res1 = context.getResource("file://" + file.getPath());
		displayInfo(res1);
		
		Resource res2 = context.getResource("classpath:test.txt");
		displayInfo(res2);
		
		Resource res3 = context.getResource("http://www.google.com");
		displayInfo(res3);
		
	}
	
	public static void displayInfo(Resource res) throws Exception {
		System.out.println(res.getClass());
		System.out.println(res.getURL());
		//System.out.println(res.getURL().getContent());
		System.out.println("");
	}

}
