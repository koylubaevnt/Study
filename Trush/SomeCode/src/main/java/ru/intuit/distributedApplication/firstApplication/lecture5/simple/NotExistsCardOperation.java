package ru.intuit.distributedApplication.firstApplication.lecture5.simple;

import java.rmi.RemoteException;

public class NotExistsCardOperation extends RemoteException {

	public NotExistsCardOperation() {
		// TODO Auto-generated constructor stub
	}

	public NotExistsCardOperation(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotExistsCardOperation(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}


}
