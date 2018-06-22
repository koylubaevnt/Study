package com.brysekkel.concurrency;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import javax.management.RuntimeErrorException;

public class ActiveObjectDemo {

	private ExecutorService ex = Executors.newSingleThreadExecutor();
	private Random random = new Random(47);
	
	private void pause(int factor) {
		try {
			TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(factor));
		} catch (InterruptedException e) {
			System.out.println("sleep() interrupted");
		}
	}
	
	public Future<Integer> calculateInt(final int x, final int y) {
		return ex.submit(new Callable<Integer>() {
			
			@Override
			public Integer call() throws Exception {
				System.out.println("starting " + x + " + " + y);
				pause(500);
				return x + y;
			}
		});
	}
	
	public Future<Float> calculateFloat(final float x, final float y) {
		return ex.submit(new Callable<Float>() {
			
			@Override
			public Float call() throws Exception {
				System.out.println("starting " + x + " + " + y);
				pause(2000);
				return x + y;
			}
		});
	}
	
	public void shutdown() {
		ex.shutdown();
	}
	
	public static void main(String[] args) {
		ActiveObjectDemo d1 = new ActiveObjectDemo();
		
		List<Future<?>> result = new CopyOnWriteArrayList<Future<?>>();
		for(float f = 0.0f; f < 1.0f; f += 0.2f)
			result.add(d1.calculateFloat(f, f));
		for(int i = 0; i < 5; i++)
			result.add(d1.calculateInt(i, i));
		System.out.println("All asynch calls made");
		while (result.size() > 0) {
			for(Future<?> f : result) {
				if(f.isDone()) {
					try {
						System.out.println(f.get());
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					result.remove(f);
				}
			}
		}
		d1.shutdown();
	}

}
