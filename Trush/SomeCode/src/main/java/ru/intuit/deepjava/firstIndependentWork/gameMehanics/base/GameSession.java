package ru.intuit.deepjava.firstIndependentWork.gameMehanics.base;

import java.util.Date;

import ru.intuit.deepjava.firstIndependentWork.base.UserSession;
import ru.intuit.deepjava.firstIndependentWork.utils.TimeHelper;

public class GameSession {

	private UserSession firstUser;
	private UserSession secondUser;
	private GameState gameState;
	private UserSession currentStepUser;
	private Date startGame;
	private Date endGame;
	private Field field;
	
	public GameSession(UserSession firstUser, UserSession secondUser) {
		this.firstUser = firstUser;
		this.secondUser = secondUser;
		this.gameState = GameState.INITIALIZATION;
		initializeResources();
	}

	private void initializeResources() {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		gameState = GameState.PLAY;
		startGame = TimeHelper.getCurrentTime();
		currentStepUser = firstUser;
	}
	
	public void nextStep() {
		currentStepUser = (currentStepUser == firstUser) ? secondUser : firstUser;
	}
	
	public void finish() {
		gameState = GameState.FINISH;
		endGame = TimeHelper.getCurrentTime();
	}

	public UserSession getFirstUser() {
		return firstUser;
	}

	public UserSession getSecondUser() {
		return secondUser;
	}

	public GameState getGameState() {
		return gameState;
	}

	public Date getStartGame() {
		return startGame;
	}

	public Date getEndGame() {
		return endGame;
	}

	public Field getField() {
		return field;
	}
	
}
