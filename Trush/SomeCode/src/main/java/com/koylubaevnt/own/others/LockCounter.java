package com.koylubaevnt.own.others;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements Counter {

	private long counter = 0;
	Lock lock = new ReentrantLock();//ReentalLock(true) - честная болкировка - кто раньше пришел, тот захватывает первым
	
	@Override
	public long inc() {
		lock.lock();
		try {
			counter++;
		} finally {
			lock.unlock();
		}
		return counter;
	}

}
