package ru.intuit.deepjava.firstIndependentWork.base;

public interface MessageSystem {

	void sendMessage(Message message);
	
	void execForAbonent(Abonent abonent);
	
	AddressService getAddressService();
	
	void addService(Abonent abonent);
	
}
