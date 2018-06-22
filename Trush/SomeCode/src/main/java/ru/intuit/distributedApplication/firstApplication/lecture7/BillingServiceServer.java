package ru.intuit.distributedApplication.firstApplication.lecture7;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class BillingServiceServer {

	public static void main(String[] args) {
		try {
			ORB orb = ORB.init(args, null);
			BillingServiceImpl BSRef = new BillingServiceImpl(orb);
			orb.connect(BSRef);
			System.out.println(BSRef);
			Object objRef = orb.resolve_initial_references("NameService");
			NamingContext ncRef = NamingContextHelper.narrow(objRef);
			NameComponent nc = new NameComponent("BillingService", "");
			NameComponent path[] = { nc };
			ncRef.rebind(path, BSRef);
			orb.run();
		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}
