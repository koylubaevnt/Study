package com.brysekkel.typeinfo.containers;

public abstract class Test<C> {
	String name;
	
	public Test() {
		this(null);
	}
	
	public Test(String name) {
		this.name = name;
	}
	
	abstract int test(C container, TestParam tp);
}
