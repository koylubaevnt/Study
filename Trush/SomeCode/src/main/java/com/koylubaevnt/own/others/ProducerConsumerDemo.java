package com.koylubaevnt.own.others;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProducerConsumerDemo {

	static Logger log = Logger.getLogger(ProducerConsumerDemo.class.getName());
	static List<String> list = new ArrayList<>();
	
	static class Consumer extends Thread {
		Object lock;
		
		public Consumer(Object lock) {
			this.lock = lock;
		}
		
		@Override
		public void run() {
			synchronized (lock) {
				try {
					log.info("[CONSUMER] Waiting for data...");
					while(list.isEmpty()) {
						lock.wait();
					}
					log.info("[CONSUMER] Processing data.");
					list.remove(list.size() - 1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.info("[CONSUMER] Data recived. list size: " + list.size());
		}
	}
	
	static class Producer extends Thread {
		Object lock;
		
		public Producer(Object lock) {
			this.lock = lock;
		}
		
		@Override
		public void run() {
			log.info("[PRODUCER] Preparing data...");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.info("[PRODUCER] Data prepared.");
			
			synchronized (lock) {
				list.add("READY");
				lock.notifyAll();
			}
		}
	}
	
	public static void main(String[] args) {
		Object lock = new Object();
		new Consumer(lock).start();
		new Producer(lock).start();

	}

}
