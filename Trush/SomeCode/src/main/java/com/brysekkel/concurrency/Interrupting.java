package com.brysekkel.concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class SleepBlocking implements Runnable {
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		System.out.println("Exiting SleepBlocking.run()");
	}
}

class IOBlocking implements Runnable {
	private InputStream in;
	public IOBlocking(InputStream in) {
		this.in = in;
	}
	@Override
	public void run() {
		try {
			System.out.println("Waiting for read():");
			in.read();
		} catch (IOException e) {
			if(Thread.currentThread().isInterrupted())
				System.out.println("Interrupted from blocked I/O");
			else
				throw new RuntimeException(e);
		}
		System.out.println("Exiting IOBlocking.run()");
	}
}


class SynchronizedBlocking implements Runnable {
	
	public synchronized void f() {
		while (true) {
			Thread.yield();//Блокировка никогда не устанавливается
		}
	}
	
	public SynchronizedBlocking() {
		new Thread() {
			@Override
			public void run() {
				f();//Блокировка устанавливается этим потоком
			}
		}.start();
	}
	
	@Override
	public void run() {
		System.out.println("Trying to call f()");
		f();
		System.out.println("Exiting SynchronizedBlocking.run()");
	}
}

public class Interrupting {

	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	static void test(Runnable r) throws InterruptedException {
		Future<?> f = exec.submit(r);
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("Interrupting " + r.getClass().getName());
		f.cancel(true);
		System.out.println("Interrupt sent to " + r.getClass().getName());
	}
	
	public static void main(String[] args) throws Exception {
		test(new SleepBlocking());
		test(new IOBlocking(System.in));
		test(new SynchronizedBlocking());
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Aborting with System.exit(0)");
		System.exit(0); //... т.к. два последних прерывания завершились неудачей 
	}

}
