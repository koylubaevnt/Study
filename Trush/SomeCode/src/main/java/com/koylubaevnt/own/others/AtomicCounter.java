package com.koylubaevnt.own.others;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicCounter implements Counter {

	private AtomicLong val = new AtomicLong(0);
	
	@Override
	public long inc() {
		return val.getAndIncrement();
	}

}
