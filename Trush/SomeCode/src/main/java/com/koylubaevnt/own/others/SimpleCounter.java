package com.koylubaevnt.own.others;

public class SimpleCounter implements Counter {

	public long counter = 0;
	
	@Override
	public long inc() {
		return counter++;
	}

}
