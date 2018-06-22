package ru.intuit.deepjava.firstIndependentWork.base;

public interface AddressService {

	void setAddresses(Abonent abonent);
	
	Address getAddress(Class<?> abonentClass);
	
}
