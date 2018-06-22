package ru.intuit.distributedApplication.firstApplication.lecture5.complex;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс <code>BillingServiceImpl</code> представляет собой удаленный объект, который реализует удаленный 
 * интерфейс {@link BillingService}.<br/>
 * Клиент взаимодействует с объектом класса <code>BillingServiceImpl<code>,вызывая методы 
 * {@link #addNewCard}, {@link #addMoney}, {@link #subMoney}, {@link #getCardBalance} интерфейса 
 * <code>BillingService</code> для обработки информации по пластиковым картам.<br/>
 * Класс <code>BillingServiceImpl</code> хранит сведения о картах в хэш-таблице, содержащей баланс 
 * пластиковой карты с именем посетителя (personName) и номером пластиковой карты ( card ), где номер 
 * карты является ключом таблицы.
 * 
 * @author KojlubaevNT
 *
 */
public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {

	/*
	 * Компиляция:
	 * C:\Java\Projects\Eclipse\Studying\src\main\java\ru\intuit\distributedApplication\firstApplication\lecture5\complex>javac -d C:\Java\Test\ NotExistsCardOperation.java Card.java CardOperation.java BillingService.java BillingServiceImpl.java BillingClient.java
	 * Запуск сервера
	 * C:\Users\KojlubaevNT>cd C:\Java\Test
	 * C:\Java\Test>start rmiregistry
	 * C:\Java\Test>java -classpath C:\Java\Test -Djava.rmi.server.codebase=file:C:\Java\Test/ ru.intuit.distributedApplication.firstApplication.lecture5.complex.BillingServiceImpl
	 * Запуск клиента:
	 * java -classpath C:\Java\Test ru.intuit.distributedApplication.firstApplication.lecture5.complex.BillingClient
	 */
	
	private static final long serialVersionUID = 3376091762789979062L;
	//private static final Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
	private Map<String, Card> hash;
	
	protected BillingServiceImpl() throws RemoteException {
		super();
		hash = new HashMap<>();
	}

	@Override
	public void addNewCard(Card card) throws RemoteException {
		hash.put(card.getCard(), card);
	}

	@Override
	public void processOperations(CardOperation[] operations) throws RemoteException {
		for(int i = 0; i < operations.length; i++) {
			Card card = hash.get(operations[i].getCard());
			if(card == null) {
				throw new NotExistsCardOperation("for card:" + operations[i].getCard());
			}
			card.setBalance(card.getBalance() + operations[i].getAmount());
			hash.put(operations[i].getCard(), card);
		}
		
	}

	@Override
	public Card getCard(String card) throws RemoteException {
		return hash.get(card);
	}
	
	public static void main(String[] args) throws RemoteException, MalformedURLException {
		//BasicConfigurator.configure();
		//logger.info("Initialize...");
		System.out.println("Initialize...");
		BillingService billingService = new BillingServiceImpl();
		
		// Задание имени удаленного объекта
		String serviceName = "rmi://localhost/BillingService";
		//logger.info("Registry remote object in registry rmiregistry...");
		System.out.println("Registry remote object in registry rmiregistry...");
		Naming.rebind(serviceName, billingService);
		System.out.println("All done. Working...");
		
	}
	
}
