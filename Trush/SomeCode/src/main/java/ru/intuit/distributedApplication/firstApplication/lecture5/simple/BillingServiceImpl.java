package ru.intuit.distributedApplication.firstApplication.lecture5.simple;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static final Logger logger = LoggerFactory.getLogger(BillingServiceImpl.class);
	private Map<String, Double> hash;
	
	protected BillingServiceImpl() throws RemoteException {
		super();
		hash = new HashMap<>();
	}

	@Override
	public void addNewCard(String personName, String card) throws RemoteException {
		hash.put(card, Double.valueOf(0));
	}

	@Override
	public void addMoney(String card, double money) throws RemoteException {
		synchronized (hash) {
			Double d = hash.get(card);
			if(d != null) {
				d += money;
				hash.put(card, d);
			} else {
				throw new NotExistsCardOperation();
			}
		}
	}

	@Override
	public void subMoney(String card, double money) throws RemoteException {
		 synchronized (hash) {
			 Double d = hash.get(card);
			if(d != null) {
				d -= money;
				hash.put(card, d);
			} else {
				throw new NotExistsCardOperation();
			}
		 }

	}

	@Override
	public double getCardBalance(String card) throws RemoteException {
		Double d = hash.get(card);
		if(d != null) {
			return d;
		} else {
			throw new NotExistsCardOperation();
		}
	}

	public static void main(String[] args) throws RemoteException, MalformedURLException {
		BasicConfigurator.configure();
		logger.info("Initialize...");
		BillingService billingService = new BillingServiceImpl();
		
		// Задание имени удаленного объекта
		String serviceName = "rmi://localhost/BillingService";
		logger.info("Registry remote object in registry rmiregistry...");
		Naming.rebind(serviceName, billingService);
		
	}
	
}
