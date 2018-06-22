package com.brysekkel.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BlockedMutex {
	
	private Lock lock = new ReentrantLock();
	
	public BlockedMutex() {
		//немедленное получение блокировки для демонстрации
		//прерывания задач, заблокированных по ReentrantLock 
		lock.lock();
	}
	
	public void f() {
		try {
			//Никогда не будет доступен для второй задачи
			lock.lockInterruptibly(); //Специальный вызов
			System.out.println("lock acquired in f()");
		} catch (InterruptedException e) {
			System.out.println("Interrupted from lock acqusition in f()");
		}
	}
}

class Blocked2 implements Runnable {
	
	BlockedMutex blocked = new BlockedMutex();
	
	@Override
	public void run() {
		System.out.println("Waiting for f() in BlockedMutex");
		blocked.f();
		System.out.println("Broken out of blocked call");
	}
	
}

public class Interrupting2 {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Blocked2());
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Issuing thread.interrupt()");
		thread.interrupt();
	}

}
