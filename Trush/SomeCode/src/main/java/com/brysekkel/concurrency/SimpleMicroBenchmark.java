package com.brysekkel.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

abstract class Incrementable {
	
	protected long counter = 0;
	
	public abstract void increment();
}

class SynchronizingTest extends Incrementable {
	
	@Override
	public synchronized void increment() {
		++counter;		
	}
}

class LockingTest extends Incrementable {
	private Lock lock = new ReentrantLock();
	
	@Override
	public void increment() {
		lock.lock();
		try {
			++counter;
		} finally {
			lock.unlock();
		} 	
	}
}


public class SimpleMicroBenchmark {

	static long test(Incrementable incrementable) {
		long start = System.nanoTime();
		for(long i = 0; i < 10_000_000L; i++)
			incrementable.increment();
		return System.nanoTime() - start;
	}
	
	public static void main(String[] args) {
		long lockTime = test(new LockingTest());
		long syncTime = test(new SynchronizingTest());
		System.out.printf("synchronized: %1$10d\n", syncTime);
		System.out.printf("Lock: %1$10d\n", lockTime);
		System.out.printf("Lock/synchronized: %1$.3f", (double) lockTime / syncTime);
	}

}
