package com.brysekkel.concurrency;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.brysekkel.typeinfo.generics.Generator;
import com.brysekkel.utils.notmine.BasicGenerator;

class ExchangerProducer<T> implements Runnable {
	
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	
	public ExchangerProducer(Exchanger<List<T>> exch, Generator<T> gen, List<T> holder) {
		exchanger = exch;
		generator = gen;
		this.holder = holder;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				for(int i = 0; i < ExchangerDemo.size; i++) {
					holder.add(generator.next());
				}
				holder = exchanger.exchange(holder);
			}
		} catch (InterruptedException e) {
			// Допустимый способ завершения
		}
	}
}

class ExchangerConsumer<T> implements Runnable {
	
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	
	public ExchangerConsumer(Exchanger<List<T>> exch, List<T> holder) {
		exchanger = exch;
		this.holder = holder;
	}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				holder = exchanger.exchange(holder);
				for(T x : holder) {
					value = x;
					holder.remove(x); //можно для CopyOnWriteArray (не вызывает ConcurrentModificationException)
				}
				
			}
		} catch (InterruptedException e) {
			// Допустимый способ завершения
		}
		System.out.println("Final value: " + value);
	}
	
}


public class ExchangerDemo {

	static int size = 10;
	static int delay = 5;
	
	public static void main(String[] args) throws Exception {
		if(args.length > 0) {
			size = new Integer(args[0]);
		}
		if(args.length > 1) {
			delay = new Integer(args[1]);
		}
		ExecutorService exec = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<>();
		List<Fat> 	producerList = new CopyOnWriteArrayList<>(),
					consumerList = new CopyOnWriteArrayList<>();
		exec.execute(new ExchangerProducer(xc, BasicGenerator.create(Fat.class), producerList));
		exec.execute(new ExchangerConsumer(xc, consumerList));
		TimeUnit.SECONDS.sleep(delay);
		exec.shutdownNow();

	}

}
