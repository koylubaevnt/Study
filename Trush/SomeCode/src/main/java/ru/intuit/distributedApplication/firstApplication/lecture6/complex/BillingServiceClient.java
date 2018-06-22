package ru.intuit.distributedApplication.firstApplication.lecture6.complex;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class BillingServiceClient {

	private static BillingService billingService;
	
	/*
	 * javac -d C:\Programming\Temp\ *.java
	 * start orbd
	 * start servertool
	 * servertool > register -server ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingServiceServer -classpath C:\Programming\Temp\ 
	 * start java ru.intuit.distributedApplication.firstApplication.lecture6.complex.BillingServiceClient
	 */

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			Object objRef = orb.resolve_initial_references("NameService");
			
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "BillingService";
			billingService = BillingServiceHelper.narrow(ncRef.resolve_str(name));
			
			System.out.println("Obtained a handle on srerver objct: " + billingService);
			
			Card card = billingService.addNewCard("Ivan", "1");
			card.addMoney(10);
			System.out.println(card.getBalance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
