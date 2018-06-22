package ru.intuit.distributedApplication.firstApplication.lecture5.complex;

import java.rmi.RemoteException;

public class NotExistsCardOperation extends RemoteException {

	private static final long serialVersionUID = -8420921230484376576L;

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
