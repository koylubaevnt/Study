package com.apress.prospring4.ch5;

public class WorkerBean {

	public void doSomeWork(int noIfTimes) {
		for(int i = 0; i < noIfTimes; i++) {
			work();
		}
	}
	
	private void work() {
		System.out.println("");
	}
}
