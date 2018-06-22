package ru.intuit.deepjava.firstIndependentWork.base;

public interface AccountService extends Abonent, Runnable {

	MessageSystem getMessageSystem();
	
	Integer getUserId(String name);
	
}
