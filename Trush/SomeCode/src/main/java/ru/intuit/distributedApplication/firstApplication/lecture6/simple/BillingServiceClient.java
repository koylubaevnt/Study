package ru.intuit.distributedApplication.firstApplication.lecture6.simple;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class BillingServiceClient {

	/*
	 * javac -d C:\Java\Test\ BillingService.java BillingServiceClient.java BillingServiceHelper.java BillingServiceHolder.java BillingServiceImpl.java BillingServiceOperations.java BillingServicePOA.java BillingServiceServer.java _BillingServiceStub.java
	 * start tnameserv.exe
	 * start java ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingServiceServer -ORBInitialHost=alpha
	 * java ru.intuit.distributedApplication.firstApplication.lecture6.simple.BillingServiceClient -ORBInitialHost=alpha
	 */
	private static BillingService billingService;
	
	public static void main(String[] args) {

		try {
			ORB orb = ORB.init(args, null);
			Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			String name = "BillingService";
			billingService = BillingServiceHelper.narrow(ncRef.resolve_str(name));
			System.out.println("Obtained a handle on server object: " + billingService);
			
			billingService.addNewCard("Test", "1");
			billingService.addMoney("1", 1234);
			System.out.println(billingService.getCardBalance("1"));
		} catch (NotFound | CannotProceed | InvalidName | org.omg.CORBA.ORBPackage.InvalidName e) {
			System.out.println("ERROR " + e);
			e.printStackTrace(System.out);
		}
	}

}
