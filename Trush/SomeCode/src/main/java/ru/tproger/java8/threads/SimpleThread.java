package ru.tproger.java8.threads;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class SimpleThread {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите номер версии метода: ");
		int version = scanner.nextInt();
		switch (version) {
		case 1:
			firstVersion();
			break;
		case 2:
			secondVersion();
			break;
		default:
			System.out.println("Не правильный номер версии метода");
			break;
		}
		
	}

	public static void firstVersion() {
		Runnable task = () -> {
		    String threadName = Thread.currentThread().getName();
		    System.out.println("Hello " + threadName);
		};
		 
		task.run();
		 
		Thread thread = new Thread(task);
		thread.start();
		 
		System.out.println("Done!");
	}
	
	public static void secondVersion() {
		Runnable runnable = () -> {
		    try {
		        String name = Thread.currentThread().getName();
		        System.out.println("Foo " + name);
		        TimeUnit.SECONDS.sleep(1);
		        System.out.println("Bar " + name);
		    }
		    catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		};
		 
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
