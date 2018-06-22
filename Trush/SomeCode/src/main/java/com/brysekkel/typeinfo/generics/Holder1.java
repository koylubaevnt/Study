package com.brysekkel.typeinfo.generics;

class Automobile {}

public class Holder1 {

	private Automobile a;
	
	public Holder1(Automobile a) {
		this.a = a;
	}
	
	public Automobile get() {
		return a;
	}
}
