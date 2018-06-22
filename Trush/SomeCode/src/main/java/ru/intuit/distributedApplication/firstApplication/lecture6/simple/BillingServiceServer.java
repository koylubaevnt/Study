package ru.intuit.distributedApplication.firstApplication.lecture6.simple;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BillingServiceServer {

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			
			//Получить ссылку к rootpoa и активируем POAManager
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			
			//Создадим servant и зарегистрируем его с ORB
			BillingServiceImpl billingServiceImpl = new BillingServiceImpl();
			billingServiceImpl.setORB(orb);
			
			//Получим объект ссылку из servant
			Object ref = rootPOA.servant_to_reference(billingServiceImpl);
			BillingService href = BillingServiceHelper.narrow(ref);
			
			Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			String name = "BillingService";
			NameComponent path[] = ncRef.to_name(name);
			ncRef.rebind(path, href);
			
			System.out.println("BillingServiceServer ready and waiting...");
			orb.run();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("BillingServiceServer exiting...");

	}

}
