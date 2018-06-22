package ru.intuit.deepjava.firstIndependentWork.gameMehanics.base;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.UserSession;

public interface GameMehanics extends Abonent, Runnable{

	//Обработка сообщений от Frontend(команды пользователей)
	void processMessages();
	
	//Расчет изменений не связанных с текущими командами пользователей
	void doGameMehanicStep();
	
	//Отпарвка на Frontend изменений UserSession
	void replicateGamesToFrontend();
	
	void startGameSession(UserSession firstUser, UserSession secondUser);
}
