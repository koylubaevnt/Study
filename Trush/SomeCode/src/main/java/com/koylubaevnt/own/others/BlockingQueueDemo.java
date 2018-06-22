package com.koylubaevnt.own.others;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockingQueueDemo {

	static Logger log = LoggerFactory.getLogger(BlockingQueueDemo.class);
	
	public static final int COUNT = 5;
	public static final String PATH = "";
	public static final Pattern PATTERN = Pattern.compile("Payment");
	
	public static void main(String[] args) throws InterruptedException {
		log.info("Starting searching...");
		final long start = System.nanoTime();
		
		for(int i = 0; i < COUNT; i++) {
			long tmp = System.nanoTime();
			//One thread impl
			oneThread(PATH, PATTERN);
			// 1 Reader -> 1 Searcher
			producerConsumer(PATH, PATTERN);
			// 1 Reader -> Pool Readers
			pool(PATH, PATTERN);
			log.info("Iteration: " + i + " in " + (System.nanoTime() - tmp) / 1e6 + "ms");
		}
		double elapsed = ((double) (System.nanoTime() - start)) / (1e6 * COUNT);
		System.out.println("Completed in " + elapsed + "ms");
	}
	
	//Все в одном потоке
	public static void oneThread(String path, Pattern pattern) {
		Util.readDir(path, (item) -> {
			String line;
			try {
				line = new String(Util.readContent(item));
				boolean result = Util.search(line, pattern);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		});
	}
	
	//Паттерн передачи данных через очередь
	public static void producerConsumer(String path, Pattern pattern) throws InterruptedException {
		//Очередь - нужно шарить очередь между потоком, который читает и который пишет
		BlockingQueue<QueueItem> queue = new ArrayBlockingQueue<>(32);
		
		//поток на поиск данных
		SearchWorker<QueueItem> worker = new SearchWorker<>(queue, pattern);
		worker.start();
		
		//В главном потоке читаем контент
		Util.readDir(path, (item) -> {
			try {
				String line = new String(Util.readContent(item));
				queue.put(new QueueItem(line, item));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		//Больше данных нет
		worker.setNoTask(true);
		
		//Ждем, когда воркер все обработает
		worker.join();
	}
	
	//на чтение работает пул потоков
	public static void pool(String path, Pattern pattern) throws InterruptedException {
		BlockingQueue<QueueItem> queue = new ArrayBlockingQueue<>(32);
		SearchWorker<QueueItem> worker = new SearchWorker<>(queue, pattern);
		worker.start();

		final int threadCount = Runtime.getRuntime().availableProcessors();
		log.info("Running read on " + threadCount + " processors.");
		
		ExecutorService service = Executors.newFixedThreadPool(threadCount);
		
		//В главном потоке читаем контент
		Util.readDir(path, (item) -> {
			service.submit(() -> {
				try {
					String line = new String(Util.readContent(item));
					queue.put(new QueueItem(line, item));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		});
		
		//Все данные прочитаны, запрещаем добавлять новые задачи
		service.shutdown();
		//Ждем заверщения всех задач потоки читатели
		service.awaitTermination(100, TimeUnit.SECONDS);
		
		//Больше данных нет
		worker.setNoTask(true);
		//Ждем, когда воркер все обработает
		worker.join();
	}
	
}
