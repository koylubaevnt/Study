package com.brysekkel.concurrency;

public class SelfManagment implements Runnable {

	private int countDown = 5;
	private Thread thread = new Thread(this);
	
	public SelfManagment() {
		thread.start();
	}
	
	@Override
	public String toString() {
		return Thread.currentThread().getName() + "(" + countDown + "), ";
	}
	
	@Override
	public void run() {
		while(true) {
			System.out.print(this);
			if(--countDown == 0)
				return;
		}
	}

	public static void main(String[] args) {
		for(int i = 0; i < 5; i++)
			new SelfManagment();
	}

}
