package com.koylubaevnt.algorithms.utils;

import java.util.Random;

public class Timer {

	private long start;
	
	public Timer() {
		start = System.currentTimeMillis();
	}
	
	public void reinitializeTime() {
		start = System.currentTimeMillis();
	}

	public double elapsedTime() {
		long now = System.currentTimeMillis();
		return (now - start);
	}

	public static void main(String[] args) {
		int a, b;
		int n = 10000;
		int[] array = new int[n];
		Random random;
		
		Timer timer = new Timer();
		random = new Random(System.currentTimeMillis());
	    for(int i = 0; i < n; i++) {
			a = -1000000;
			b = 1000000;
			array[i] = a + random.nextInt(b - a);
		}
		double time = timer.elapsedTime();
		System.out.println(time + " секунд");
	}
}
