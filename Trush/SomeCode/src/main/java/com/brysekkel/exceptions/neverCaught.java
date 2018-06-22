package com.brysekkel.exceptions;

public class neverCaught {

	static void f() {
		throw new RuntimeException("From f()");
	}
	
	static void g() {
		f();
	}
	
	public static void main(String[] args) {
		g();

	}

}
