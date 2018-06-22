package ru.intuit.deepjava.firstIndependentWork.gameMehanics;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.MessageSystem;
import ru.intuit.deepjava.firstIndependentWork.base.UserSession;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.GameMehanics;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.GameSession;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.PointState;
import ru.intuit.deepjava.firstIndependentWork.utils.TimeHelper;

public class GameMehanicsImpl implements GameMehanics {

	private final int SLEEP_TIME;
	
	private final Map<Integer, GameSession> userIdToGameSessions =
			new HashMap<>();
	private final MessageSystem messageSystem;
	private final Address address;
	
	public GameMehanicsImpl(MessageSystem messageSystem) {
		initializeResources();
		SLEEP_TIME = 1000;
		this.messageSystem = messageSystem;
		this.address = new Address();
		messageSystem.addService(this);
	}
	
	public void initializeResources() {
		
	}
	
	@Override
	public void processMessages() {
		messageSystem.execForAbonent(this);		
	}

	@Override
	public void doGameMehanicStep() {
		// stub		
	}

	@Override
	public void replicateGamesToFrontend() {
		Iterator<Integer> keyIterator = userIdToGameSessions.keySet().iterator();
		while(keyIterator.hasNext()) {
			Integer userId = keyIterator.next();
			GameSession gameSession = userIdToGameSessions.get(userId);
			
		}
		
	}

	@Override
	public void startGameSession(UserSession firstUser, UserSession secondUser) {
		GameSession gameSession = new GameSession(firstUser, secondUser);
		userIdToGameSessions.put(firstUser.getUserId(), gameSession);
		firstUser.setColor(PointState.BLUE.toString());
		firstUser.setField(gameSession.getField());
		
		userIdToGameSessions.put(secondUser.getUserId(), gameSession);
		secondUser.setColor(PointState.RED.toString());
		secondUser.setField(gameSession.getField());
		
		gameSession.start();
	}

	public GameSession getGameSession(UserSession userSession) {
		return userIdToGameSessions.get(userSession.getUserId());
	}
	
	@Override
	public Address getAddress() {
		return address;
	}

	@Override
	public void run() {
		while (true) {
			processMessages();
			doGameMehanicStep();
			replicateGamesToFrontend();
			TimeHelper.sleep(SLEEP_TIME);
		}
		
	}

	/*
	public void setIdFromName(String name) {
		try {
			userId = getId(name);
		} catch (GameMehanicsException e) {
			log.error(e.getMessage())
		}
	}
	
	private int getId(String name) throws GameMehanicsException {
		DatabaseManager manager = context.get(DatabaseManager.class);
		if(manager == null) {
			throw new GameMehanicsException("Не могу найти DBManager");
		}
		return manager.readId(name);
	}
	*/
	
}
