package ru.intuit.deepjava.firstIndependentWork.messageSystem;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.FrontendService;
import ru.intuit.deepjava.firstIndependentWork.base.Message;

public abstract class MessageToFrontend extends Message {

	public MessageToFrontend(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if(abonent instanceof FrontendService) {
			exec((FrontendService)abonent);
		}
	}
	
	public abstract void exec(FrontendService abonent);

}
