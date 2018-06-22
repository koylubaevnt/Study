package ru.intuit.deepjava.firstIndependentWork.messageSystem;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.AccountService;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.Message;

public abstract class MessageToAcountService extends Message {

	public MessageToAcountService(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if(abonent instanceof AccountService) {
			exec((AccountService) abonent);
		}
	}
	
	public abstract void exec(AccountService abonent);

}
