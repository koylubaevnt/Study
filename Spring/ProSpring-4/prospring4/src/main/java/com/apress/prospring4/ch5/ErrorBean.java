package com.apress.prospring4.ch5;

public class ErrorBean {
	
	public void errorPhoneMethod() throws Exception {
		throw new Exception("Foo");
	}
	
	public void otherErrorPhoneMethod() throws IllegalArgumentException {
		throw new IllegalArgumentException("Bar");
	}
	
	
}
