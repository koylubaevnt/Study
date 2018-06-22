package com.koylubaevnt.stepik.java.basecourse.loggers;


public class StolenPackageException extends RuntimeException {

	public StolenPackageException() {
		super();
	}
	
	public StolenPackageException(String message) {
		super(message);
	}
	
	public StolenPackageException(String message, Throwable cause) {
		super(message, cause);
	}
}
