package com.koylubaevnt.own.others;

import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchWorker<T extends QueueItem> extends Thread {
	
	private static Logger log = LoggerFactory.getLogger(SearchWorker.class);
	
	//Очередь заданий
	private BlockingQueue<T> sourceQueue;
	//Паттерн для поиска
	private Pattern pattern;
	//метка что в очередь больше ничего не придет
	private volatile boolean noTask;
	
	public SearchWorker(BlockingQueue<T> sourceQueue, Pattern pattern) {
		this.sourceQueue = sourceQueue;
		this.pattern = pattern;
	}

		public void setNoTask(boolean noTask) {
		this.noTask = noTask;
	}

	@Override
	public void run() {
		log.debug("Worker running: " + Thread.currentThread().getName());
		while(!Thread.currentThread().isInterrupted() && !(noTask && sourceQueue.isEmpty())) {
			try {
				long start = System.nanoTime();
				T task = sourceQueue.take();
				long elapsed = System.nanoTime() - start;
				if(elapsed > 1e9) {
					log.warn("Waiting on empty queue: " + (elapsed) / 1e6 + "ms");
				}
				if(task != null) {
					if(Util.search(task.getData(), pattern)) {
						log.info("Found in " + task.getPath());
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			log.debug("Worker terminated: " + Thread.currentThread().getName());
		}
	}
}
