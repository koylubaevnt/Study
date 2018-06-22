package ru.intuit.distributedApplication.firstApplication.lecture5.simple;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class BillingClient {

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		String objectName = "rmi://localhost/BillingService";
		System.out.println("Starting...");
		
		BillingService billingService = (BillingService) Naming.lookup(objectName);
		System.out.println("Recived link to remote object from rmiregistry");
		
		billingService.addNewCard("Piter", "1");
		billingService.addNewCard("Piter", "2");
		billingService.addNewCard("Piter", "3");
		System.out.println("Added new card");
		
		for(int i = 0; i < 10000; i++) {
			try {
				billingService.addMoney("1", 1);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				billingService.addMoney("2", 1);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			try {
				billingService.addMoney("3", 1);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Added money to cards");
		
		System.out.println("1: " + billingService.getCardBalance("1"));
		System.out.println("2: " + billingService.getCardBalance("2"));
		System.out.println("3: " + billingService.getCardBalance("3"));
	}

}
