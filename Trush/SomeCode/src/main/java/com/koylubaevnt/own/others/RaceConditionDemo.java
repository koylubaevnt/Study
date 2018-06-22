package com.koylubaevnt.own.others;

/*
 * Race Condition
 * 	- ограничиваем доступ к общему ресурсу!
 * 
 * Starvation (простаивание)
 * 	- честные блокировки (ReentrantLock(true))
 * 
 * Deadlock
 * 	- упорядочить взятие блокировки. Т.е. всегда сначала захват одного ресурса, а затем другого для 2х потоков
 *  - использовтаь tryLock()
 *  - исопльзовтаь таймаут
 */

public class RaceConditionDemo {

	public static void main(String[] args) throws InterruptedException {
		final int threadNum = 2;
		Counter counter = new SimpleCounter();
		Thread[] threads = new Thread[threadNum];
		for(int i = 0; i < threadNum; i++) {
			threads[i] = new Sequencer(counter);
			threads[i].start();
		}
		
		for(Thread thread : threads) {
			thread.join();
		}
		
		System.out.printf("Threads: %d\nCounter: %d" ,threadNum, counter);
	}
	
	static class Sequencer extends Thread {
		
		private Counter counter;
		
		public Sequencer(Counter counter) {
			this.counter = counter;
		}
		
		@Override
		public void run() {
			for(int i = 0; i < 100_000; i++) {
				counter.inc();
			}
		}
	}
}
