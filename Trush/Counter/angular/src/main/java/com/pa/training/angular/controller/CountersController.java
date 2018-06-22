package com.pa.training.angular.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pa.training.angular.domain.Counter;
import com.pa.training.angular.domain.Counter.Status;

@RestController
@RequestMapping("/api/counters")
public class CountersController {

	public static final ConcurrentMap<String, Counter> counters = new ConcurrentHashMap<>();
	public static final ConcurrentMap<String, Thread> threads = new ConcurrentHashMap<>();
	
	private static int counterId = 1;
	private static final Random rnd = new Random(13);
	
	private static final Logger LOG = LoggerFactory.getLogger(CountersController.class);
	
	@GetMapping("/")
	public List<Counter> getCounters() {
		List<Counter> list = new ArrayList<>(counters.values());
		LOG.info("getCounters > list={}. counters={}", list, counters);
		return list;
	}
	
	@GetMapping("/{id}")
	public Counter getCounterId(@PathVariable String id) {
		Counter conter = counters.get(id);
		LOG.info("getCounterId > id={}, conter={}. counters={}", id, conter, counters);
		return conter;
	}
	
	@PostMapping("/add")
	public Counter add(@RequestBody Counter data) {
		LOG.info("add > Start. counters={}", counters);
		final Counter counter = new Counter(counterId + "", data.getMax() <= 0 ? rnd.nextInt(500) : data.getMax());
		counterId++;
		counters.putIfAbsent(counter.getId(), counter);
		LOG.info("add > Finish. counters={}", counters);
		return counters.get(counter.getId());
	}
	
	@DeleteMapping("/{id}")
	public Counter delete(@PathVariable String id) {
		LOG.info("delete > Start. id={}, counters={}, threads={}", id, counters, threads);
		Thread thread = threads.remove(id);
		LOG.info("delete > thread={}", thread);
		if(thread != null) thread.interrupt();
		Counter removed = counters.remove(id); 
		LOG.info("delete > Start. removed={}, counters={}", removed, counters);
		return removed;
	}
	
	@PostMapping("/start")
	public Counter start(@RequestBody Counter data) {
		LOG.info("start > Start. data={}, counters={}", data, counters);
		
		final Counter counter = counters.get(data.getId());
		Status status = counter.getStatus();
		if(counter != null && !status.equals(Status.RUNNING)) {
			LOG.info("start > Start. counter={}, threads={}", counter, threads);
			Thread thread = new Thread(() -> {
				
				counter.setStatus(Status.RUNNING);
				while(counter.getMax() > counter.getCurrent() && counter.getStatus().equals(Status.RUNNING)) {
					try {
						Thread.sleep(1000);
						counter.setCurrent(counter.getCurrent() + 1);
						LOG.info(" > {}: counter={}", Thread.currentThread().getName(), counter);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						break;
					}
					
				}
				if(counter.getMax() <= counter.getCurrent()) {
					try {
						Thread.sleep(1000);
						counter.setStatus(Status.STOPPED);
						counter.setCurrent(0);
						threads.remove(counter.getId());
						LOG.info(" > {}: threads={}", Thread.currentThread().getName(), threads);
					} catch (InterruptedException e) {
						
					}
				}
			});
			threads.putIfAbsent(data.getId(), thread);
			thread.start();
			LOG.info("start > Finish. counters={}", counters);
		} else {
			
		}
		return counter;
	}
	
	@PostMapping("/pause")
	public Counter pause(@RequestBody Counter counter) {
		LOG.info("pause > Start. counter={}, counters={}", counter, counters);
		Thread thread = threads.remove(counter.getId());
		LOG.info("pause > thread={}", thread);
		if(thread != null) thread.interrupt();
		Counter paused = counters.get(counter.getId());
		paused.setStatus(Status.PAUSED);
		LOG.info("pause > Start. paused={}, counters={}", paused, counters);
		return paused;
	}
	
	@PostMapping("/stop")
	public Counter stop(@RequestBody Counter counter) {
		LOG.info("stop > Start. counter={}, counters={}", counter, counters);
		Thread thread = threads.remove(counter.getId());
		LOG.info("pause > thread={}", thread);
		if(thread != null) thread.interrupt();
		Counter stopped = counters.get(counter.getId());
		stopped.setStatus(Status.STOPPED);
		stopped.setCurrent(0);
		LOG.info("stop > Start. stopped={}, counters={}", stopped, counters);
		return stopped;
	}
}
