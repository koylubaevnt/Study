package ru.intuit.deepjava.firstIndependentWork.frontend;

import ru.intuit.deepjava.firstIndependentWork.base.AccountService;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.Message;
import ru.intuit.deepjava.firstIndependentWork.messageSystem.MessageToAcountService;

public class MessageGetUserId extends MessageToAcountService {

	private String name;
	private String sessionId;
	
	public MessageGetUserId(Address from, Address to, String sessionId, String name) {
		super(from, to);
		this.sessionId = sessionId;
		this.name = name;
	}

	@Override
	public void exec(AccountService abonent) {
		Integer userId = abonent.getUserId(name);
		Message message = new MessageUpdateUserId(getTo(), getFrom(), sessionId, userId, name);
		abonent.getMessageSystem().sendMessage(message);
	}

}
