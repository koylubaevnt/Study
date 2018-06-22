package ru.intuit.deepjava.firstIndependentWork.base;

import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.Field;
import ru.intuit.deepjava.firstIndependentWork.gameMehanics.base.PointState;

public class UserSession {

	private String sessionId;
	private String userName;
	private Integer userId;
	
	private boolean authorizationIs = false;
	private boolean errorAutorization = false;
	private boolean myTurn = false;
	private String color = PointState.BLUE.toString();
	private Field field;
	
	public UserSession(String sessionId) {
		this(sessionId, null, null);
	}

	public UserSession(String sessionId, String userName, Integer userId) {
		this.sessionId = sessionId;
		this.userName = userName;
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getUserName() {
		return userName;
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isMyTurn() {
		return myTurn;
	}

	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public boolean isErrorAutorization() {
		return errorAutorization;
	}

	public void setErrorAutorization(boolean errorAutorization) {
		this.errorAutorization = errorAutorization;
	}
	
	public void setAuthorizationIs(boolean authorizationIs) {
		this.authorizationIs = authorizationIs;
	}
	
	public boolean isAuthorizationIs() {
		return authorizationIs;
	}
	
}
