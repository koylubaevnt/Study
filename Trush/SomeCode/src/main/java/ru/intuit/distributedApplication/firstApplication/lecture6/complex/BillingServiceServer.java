package ru.intuit.distributedApplication.firstApplication.lecture6.complex;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.Policy;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.LifespanPolicyValue;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class BillingServiceServer {

	public static void main(String[] args) {

		ORB orb = ORB.init(args, null);
		try {
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			Object rootObj = orb.resolve_initial_references("NameService");
			NamingContextExt root = NamingContextExtHelper.narrow(rootObj);
			
			Policy[] policies = { 
					rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT)
					};
			POA myPOA = rootPOA.create_POA("billing_agent_poa", rootPOA.the_POAManager(), policies);
			
			BillingServiceImpl managetServant = new BillingServiceImpl();
			byte[] managerId = "BillingService".getBytes();
			myPOA.activate_object_with_id(managerId, managetServant);
			rootPOA.the_POAManager().activate();
			
			((NamingContext) root).bind(root.to_name("BillingService"), 
					myPOA.servant_to_reference(managetServant));
			System.out.println(myPOA.servant_to_reference(managetServant) + " is ready.");
			orb.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
