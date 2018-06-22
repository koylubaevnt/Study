package ru.intuit.deepjava.firstIndependentWork.frontend;

import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.FrontendService;
import ru.intuit.deepjava.firstIndependentWork.messageSystem.MessageToFrontend;

public class MessageUpdateUserId extends MessageToFrontend {

	private final String sessionId;
	private final Integer userId;
	private final String name;
	
	public MessageUpdateUserId(Address from, Address to, String sessionId, Integer userId, String name) {
		super(from, to);
		this.sessionId = sessionId;
		this.userId = userId;
		this.name = name;
	}

	@Override
	public void exec(FrontendService abonent) {
		abonent.updateUser(sessionId, userId, name);
	}

}
