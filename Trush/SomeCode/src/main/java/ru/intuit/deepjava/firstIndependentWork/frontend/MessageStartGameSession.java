package ru.intuit.deepjava.firstIndependentWork.frontend;

import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.UserSession;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.GameMehanics;
import ru.intuit.deepjava.firstIndependentWork.messageSystem.MessageToGameMehanics;

public class MessageStartGameSession extends MessageToGameMehanics {

	UserSession firstUser;
	UserSession secondUser;
	
	public MessageStartGameSession(Address from, Address to, UserSession firstUser, UserSession secondUser) {
		super(from, to);
		this.firstUser = firstUser;
		this.secondUser = secondUser;
	}

	@Override
	public void exec(GameMehanics abonent) {
		abonent.startGameSession(firstUser, secondUser);
	}

}
