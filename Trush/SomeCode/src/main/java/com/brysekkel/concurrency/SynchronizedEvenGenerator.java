package com.brysekkel.concurrency;

public class SynchronizedEvenGenerator extends IntGenerator {

	private int currentEvenValue = 0;
	
	//неяваный МЬЮТЕКС
	@Override
	public synchronized int next() {
		++currentEvenValue;
		Thread.yield(); //Повышает вероятночть переключения контекста 
		++currentEvenValue;
		return currentEvenValue;
	}

	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}
}
