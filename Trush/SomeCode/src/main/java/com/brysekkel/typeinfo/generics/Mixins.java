package com.brysekkel.typeinfo.generics;


import java.util.Date;

interface TimeStamped {
	long getStamp();
}

interface SerialNumbered {
	long getSerialNumber();
}

class TimeStampedImpl implements TimeStamped {
	private final long timeStamp;
	public TimeStampedImpl() {
		timeStamp = new Date().getTime();
	}
	@Override
	public long getStamp() {
		return timeStamp;
	}
}

class SerialNumberedImpl implements SerialNumbered {
	private static long counter = 1;
	private final long serialNumber = counter++;
	@Override
	public long getSerialNumber() {
		return serialNumber;
	}
}

interface Basic {
	public void set(String val);
	public String get();
}

class BasicImpl implements Basic {
	private String value;
	@Override
	public void set(String val) {
		value = val;
	}
	@Override
	public String get() {
		return value;
	}
}

class Mixin extends BasicImpl implements TimeStamped, SerialNumbered {
	private TimeStamped timeStamp = new TimeStampedImpl();
	private SerialNumbered serialNumber = new SerialNumberedImpl();
	@Override
	public long getSerialNumber() {
		return serialNumber.getSerialNumber();
	}
	@Override
	public long getStamp() {
		return timeStamp.getStamp();
	}
	
}

public class Mixins {

	public static void main(String[] args) {
		Mixin mixin1 = new Mixin();
		Mixin mixin2 = new Mixin();
		mixin1.set("test string 1");
		mixin2.set("test string 2");
		System.out.println(mixin1.get() + " " + 
				mixin1.getStamp() + " " + mixin1.getSerialNumber());
		System.out.println(mixin2.get() + " " + 
				mixin2.getStamp() + " " + mixin2.getSerialNumber());
	}

}
