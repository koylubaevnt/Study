package ru.intuit.deepjava.firstIndependentWork.messageSystem;

import java.util.HashMap;
import java.util.Map;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.AddressService;

public class AddressServiceImpl implements AddressService {

	private final Map<Class<?>, Address> addresses = new HashMap<>();

	public Address getAddress(Class<?> abonentClass) {
		return addresses.get(abonentClass);
	}

	public void setAddresses(Abonent abonent) {
		addresses.put(abonent.getClass(), abonent.getAddress());
	}
	
	

}
