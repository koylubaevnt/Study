package com.brysekkel.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NaiveExceptionHandling implements Runnable {

	@Override
	public void run() {
		throw new RuntimeException();
	}

	public static void main(String[] args) {
		try {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new NaiveExceptionHandling());
		} catch (RuntimeException e) {
			//Никогда не выполнится
			System.out.println("Exception has been handled!");
		}
	}

}
