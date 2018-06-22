package ru.intuit.deepjava.firstIndependentWork.messageSystem;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.Message;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.GameMehanics;

public abstract class MessageToGameMehanics extends Message {

	public MessageToGameMehanics(Address from, Address to) {
		super(from, to);
	}

	@Override
	public void exec(Abonent abonent) {
		if (abonent instanceof GameMehanics) {
			exec((GameMehanics) abonent);
		}

	}

	public abstract void exec(GameMehanics abonent);
}
