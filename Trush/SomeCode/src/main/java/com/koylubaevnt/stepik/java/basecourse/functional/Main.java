package com.koylubaevnt.stepik.java.basecourse.functional;

import java.math.BigDecimal;

public class Main {
	
	public static void main(String[] args) {
		/*
		firstVariant();
		secondVariant();
		thirdVariant();
		fourthVariant();
		*/
		Timer t = new Timer();
		System.out.println(t.measureTimeFunctionable(Main::bigDecimalPower));
	}
	
	private static void firstVariant() {
		Timer timer = new Timer();
		long time = timer.measureTime(new BigDecimalPower());
		System.out.println(time);
	}
	
	private static void secondVariant() {
		Timer timer = new Timer();
		long time = timer.measureTime(new Runnable() {
			
			@Override
			public void run() {
				BigDecimal bigDecimal = new BigDecimal("1234567").pow(100000);
				System.out.println("secondVariant");
			}
			
		});
		System.out.println(time);
	}
	
	private static void thirdVariant() {
		Timer timer = new Timer();
		long time = timer.measureTime(() -> {
			BigDecimal bigDecimal = new BigDecimal("1234567").pow(100000);
			System.out.println("thirdVariant");
			});
		System.out.println(time);
	}
	
	private static void fourthVariant() {
		Timer timer = new Timer();
		long time = timer.measureTime(Main::bigDecimalPower);
		System.out.println(time);
	}
	
	private static void bigDecimalPower() {
		BigDecimal bigDecimal = new BigDecimal("1234567").pow(100000);
		System.out.println("fourthVariant");
	}
	
	private static class BigDecimalPower implements Runnable {
		
		@Override
		public void run() {
			BigDecimal bigDecimal = new BigDecimal("1234567").pow(100000);
			System.out.println("firstVariant");
		}
		
	}
	
}
