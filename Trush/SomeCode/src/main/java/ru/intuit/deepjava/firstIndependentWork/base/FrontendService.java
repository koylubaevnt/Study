package ru.intuit.deepjava.firstIndependentWork.base;

public interface FrontendService extends Abonent, Runnable {
	
	MessageSystem getMessageSystem();
	
	void updateUser(String sessionId, Integer userId, String name);
	
}
