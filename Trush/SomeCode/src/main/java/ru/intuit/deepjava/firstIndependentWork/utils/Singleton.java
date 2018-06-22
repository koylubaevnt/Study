package ru.intuit.deepjava.firstIndependentWork.utils;

public final class Singleton {

	private Singleton() {
	}

	public static Singleton getInstance () {
		return SingletonHelper.INSTANCE;
	}
	
	private static class SingletonHelper {
		private static final Singleton INSTANCE = new Singleton();
	}
	
}
