package ru.intuit.deepjava.firstIndependentWork.accountService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.intuit.deepjava.firstIndependentWork.base.AccountService;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.MessageSystem;
import ru.intuit.deepjava.firstIndependentWork.utils.TimeHelper;

public class AccountServiceImpl implements AccountService {

	private final Map<String, Integer> fakeAccounts = new ConcurrentHashMap<>();
	
	private static final int TICK_TIME = 1000;
	
	private final MessageSystem messageSystem;
	private final Address address;
	
	
	public AccountServiceImpl(MessageSystem messageSystem) {
		this.messageSystem = messageSystem;
		this.address = new Address();
		messageSystem.addService(this);
		this.fakeAccounts.put("Tally", 1);
		this.fakeAccounts.put("Billy", 3);
		this.fakeAccounts.put("Test", 2);
		
	}
	
	@Override
	public MessageSystem getMessageSystem() {
		return messageSystem;
	}


	@Override
	public Address getAddress() {
		return address;
	}


	@Override
	public void run() {
		while(true) {
			messageSystem.execForAbonent(this);
			TimeHelper.sleep(TICK_TIME);
		}
	}

	@Override
	public Integer getUserId(String name) {
		TimeHelper.sleep(TICK_TIME);
		return fakeAccounts.get(name);
	}
	
	
	
}
