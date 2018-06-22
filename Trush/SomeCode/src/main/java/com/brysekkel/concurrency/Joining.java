package com.brysekkel.concurrency;

class Sleeper extends Thread {
	
	private int duraion;
	
	public Sleeper(String name, int sleepTime) {
		super(name);
		duraion = sleepTime;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleep(duraion);
		} catch (InterruptedException e) {
			//Если прерывает поток другой поток, то флаг interrupted сбрасывается
			System.out.println(getName() + " was interrupted. " +
					"isInterrupted(): " + isInterrupted());
			return;
		}
		System.out.println(getName() + " has awakened");
		
	}
}

class Joiner extends Thread {
	
	private Sleeper sleeper;
	
	public Joiner(String name, Sleeper sleeper) {
		super(name);
		this.sleeper = sleeper;
		start();
	}
	
	@Override
	public void run() {
		try {
			sleeper.join();
		} catch (InterruptedException e) {
			System.out.println("Interrupted");
			return;
		}
		System.out.println(getName() + " join completed");
		
	}
}

public class Joining {

	public static void main(String[] args) {
		Sleeper
			sleepy = new Sleeper("Sleepy", 1500),
			grumpy = new Sleeper("Grompy", 1500);
		Joiner
			dopey = new Joiner("Dopey", sleepy),
			doc = new Joiner("Doc", grumpy);
		grumpy.interrupt();

	}

}
