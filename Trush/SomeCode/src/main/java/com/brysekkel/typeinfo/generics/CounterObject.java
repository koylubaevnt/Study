package com.brysekkel.typeinfo.generics;

public class CounterObject {
	
	private static long counter = 0;
	private final long id = counter++;
	private long id() {
		return id;
	}
	@Override
	public String toString() {
		return "CounterObject [id=" + id + "]";
	}
}
