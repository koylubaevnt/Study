package com.brysekkel.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.brysekkel.typeinfo.arrays.Generated;
import com.brysekkel.typeinfo.arrays.RandomGenerator;

public abstract class Tester<C> {

	static int testReps = 10;
	static int testCycles = 1000;
	static int containersSize = 1000;
	abstract C containerInitializer();
	abstract void startReadersAndWriters();
	
	C testContainer;
	String testId;
	int nReareds;
	int nWriters;
	volatile long readResult = 0;
	volatile long readTime = 0;
	volatile long writeTime = 0;
	
	CountDownLatch endLatch;
	static ExecutorService exec = Executors.newCachedThreadPool();
	
	Integer[] writeData;
	
	public Tester(String testId, int nReaders, int nWriters) {
		this.testId = testId + " " + nReaders + "r " + nWriters + "w";
		this.nReareds = nReaders;
		this.nWriters = nWriters;
		writeData = Generated.array(Integer.class, 
				new RandomGenerator.Integer(), containersSize);
		for(int i = 0; i < testReps; i++) {
			runTest();
			readTime = 0;
			writeTime = 0;
		}
	}
	
	void runTest() {
		endLatch = new CountDownLatch(nReareds + nWriters);
		testContainer = containerInitializer();
		startReadersAndWriters();
		try {
			endLatch.await();
		} catch (InterruptedException e) {
			System.out.println("endLatch interrupted");
		}
		System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
		if(readTime != 0 && writeTime != 0)
			System.out.printf("%-27s %14d\n", "readTime + writeTime = ", readTime + writeTime);
	}
	
	abstract class TestTask implements Runnable {
		abstract void test();
		abstract void putResults();
		long duration;
		@Override
		public void run() {
			long startTime = System.nanoTime();
			test();
			duration = System.nanoTime() - startTime;
			synchronized (Tester.this) {
				putResults();
			}
			endLatch.countDown();
		}
	}
	
	public static void initMain(String[] args) {
		if(args.length > 0)
			testReps = new Integer(args[0]);
		if(args.length > 1)
			testCycles = new Integer(args[1]);
		if(args.length > 2)
			containersSize = new Integer(args[2]);
		System.out.printf("%-27s %14s %14s\n", "Type", "Read time", "Write time");
	}
	
}
