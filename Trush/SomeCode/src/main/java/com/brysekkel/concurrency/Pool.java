package com.brysekkel.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Pool<T> {
	
	private int size;
	private List<T> items = new ArrayList<>();
	private volatile boolean[] checkedOut;
	private Semaphore semaphore;
	
	public Pool(Class<T> classObject, int size) {
		this.size = size;
		checkedOut = new boolean[size];
		semaphore = new Semaphore(size, true);
		for(int i = 0; i < size; i++) {
			try {
				//Предполагается конструктор по умолчанию
				items.add(classObject.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public T checkOut() throws InterruptedException {
		semaphore.acquire();
		return getItem();
	}
	
	public void  checkIn(T x) throws InterruptedException {
		if(releaseItem(x))
			semaphore.release();
	}
	
	private synchronized T getItem() {
		for(int i = 0; i < size; ++i) {
			if(!checkedOut[i]) {
				checkedOut[i] = true;
				return items.get(i);
			}
		}
		return null; //семафор не позволит быть здесь!!!		
	}
	
	private synchronized boolean releaseItem(T item) {
		int index = items.indexOf(item);
		if(index == -1) return false;
		if(checkedOut[index]) {
			checkedOut[index] = false;
			return true;
		}
		return false;
			
	}
}
