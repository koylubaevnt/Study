package ru.intuit.distributedApplication.firstApplication.lecture5.complex;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;

public class BillingClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String objectName = "rmi://" + args[0] + "/BillingService";
		System.out.println("Starting...");
		
		BillingService billingService = (BillingService) Naming.lookup(objectName);
		System.out.println("Recived link to remote object from rmiregistry");
		
		Card card;
		card = billingService.getCard("1");
		if(card == null) {
			card = new Card("Piter", LocalDate.now(), "1", 0.0);
			billingService.addNewCard(card);
		}
		card = billingService.getCard("2");
		if(card == null) {
			card = new Card("Stefan", LocalDate.now(), "2", 0.0);
			billingService.addNewCard(card);
		}
		card = billingService.getCard("3");
		if(card == null) {
			card = new Card("Nataly", LocalDate.now(), "3", 0.0);
			billingService.addNewCard(card);
		}
		System.out.println("Added new card");
		int cnt = 30000;
		CardOperation[] operations = new CardOperation[cnt];
		for(int i = 0; i < operations.length; i++) {
			operations[i] = new CardOperation(String.valueOf(i % 3 + 1), i % 3 + 1, LocalDate.now());
		}
		billingService.processOperations(operations);
		System.out.println("Added money to cards");
		
		System.out.println("1: " + billingService.getCard("1"));
		System.out.println("2: " + billingService.getCard("2"));
		System.out.println("3: " + billingService.getCard("3"));
	}

}
