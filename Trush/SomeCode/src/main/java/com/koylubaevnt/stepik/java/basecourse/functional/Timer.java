package com.koylubaevnt.stepik.java.basecourse.functional;

public class Timer {

	public long measureTime(Runnable runnable) {
		long startTime = System.currentTimeMillis();
		runnable.run();
		return System.currentTimeMillis() - startTime;
	}
	
	public long measureTimeFunctionable(Functionable functionable) {
		long startTime = System.currentTimeMillis();
		functionable.method();
		return System.currentTimeMillis() - startTime;
	}
	
}
