package com.koylubaevnt.own.others;

import java.util.concurrent.TimeUnit;

public class ThreadDemo {

	static class MyThreadStop extends Thread {
		private String name;
		private volatile boolean pleaseStop;
		
		public MyThreadStop(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			System.out.println("Thread " + name + ": started");
			//while(!Thread.currentThread().isInterrupted()) {
			while(!pleaseStop) {
				try {
					System.out.println("Thread " + name + ": working...");
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Thread " + name + ": finished");
		}
	}
	
	static class MyThread extends Thread {
		private String name;
		public MyThread(String name) {
			this.name = name;
		}
		
		@Override
		public void run() {
			System.out.println("Thread " + name + ": started");
			try {
				for(int i = 0; i < 5; i++) {
					System.out.println("Thread " + name + ": " + i);
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread " + name + ": finished");
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		//inParallel();
		//start();
		join();
	}
	
	private static void join() throws InterruptedException {
		Thread t1 = new MyThread("join");
		
		// Запуск кода в новом потоке
		System.out.println("Starting thread");
		t1.start();
		System.out.println("Main join to t1.");
		t1.join();
		for(int i = 0; i < 5; i++) {
			System.out.println("Main: " + i);
			TimeUnit.SECONDS.sleep(2);
		}
		System.out.println("Main finished");
		
		
	}

	private static void inParallel() throws Exception {
		Thread t1 = new MyThread("inParallel");
		
		// Запуск кода в новом потоке
		System.out.println("Starting thread");
		t1.start();
		
		for(int i = 0; i < 5; i++) {
			System.out.println("Main: " + i);
			TimeUnit.SECONDS.sleep(2);
		}
		System.out.println("Main finished");
		
	}

	static void start() {
		Thread t1 = new MyThread("simpleThread");
		
		// Запуск кода в новом потоке
		System.out.println("Starting thread");
		t1.start();
		
		//Запуск потока в текущем потоке
		System.out.println("Running");
		t1.run();
		
		//Нельзя запустить поток еще раз
		System.out.println("Error:");
		t1.start();
	}
}
