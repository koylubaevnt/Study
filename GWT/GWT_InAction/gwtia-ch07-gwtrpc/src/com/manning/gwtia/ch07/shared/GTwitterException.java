package com.manning.gwtia.ch07.shared;

public class GTwitterException extends Exception {

	private static final long serialVersionUID = -6540744111518765143L;
	
	/**
	 * 
	 */
	public GTwitterException() {
	}

	public GTwitterException(String reason) {
		super(reason);
	}
	
}
