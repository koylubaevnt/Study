package ru.intuit.distributedApplication.firstApplication.lecture7;

import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.Request;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.UnknownUserException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

public class BillingServiceClient {

	Object billingService;
	ORB orb;
	
	/*
	 * javac -d C:\Programming\Temp\ *.java
	 * start tnameserv.exe
	 * start java ru.intuit.distributedApplication.firstApplication.lecture7.BillingServiceServer -ORBInitialHost=alpha
	 * java ru.intuit.distributedApplication.firstApplication.lecture7.BillingServiceClient -ORBInitialHost=alpha
	 */
	public static void main(String[] args) {
		BillingServiceClient billingServiceClient = new BillingServiceClient();
		
		try {
			billingServiceClient.doTest(args);
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		

	}

	void doTest(String... args) throws Exception{
		orb = ORB.init(args, null);
		Object objRef = orb.resolve_initial_references("NameService");
		NamingContext ncRef = NamingContextHelper.narrow(objRef);
		NameComponent nc = new NameComponent("BillingService", "");
		NameComponent[] path = { nc };
		billingService = ncRef.resolve(path);
		System.out.println(billingService);
		
		testAddNewCard("Ivan", "1");
		testAddMoney("1", 10.2);
		testSubMoney("1", 8.1);
		testGetCardBalance("1");
	}
	
	void testAddNewCard(String name, String card) {
		Request request = billingService._request("addNewCard");
		request.set_return_type(orb.get_primitive_tc(TCKind.tk_void));
		Any any = request.add_in_arg();
		any.insert_string(name);
		Any any2 = request.add_in_arg();
		any2.insert_string(card);
		request.invoke();
		Exception exception = request.env().exception();
		if(exception instanceof UnknownUserException) {
			UnknownUserException userEx = (UnknownUserException) exception;
		}
				
	}

	void testAddMoney(String card, Double money) {
		Request request = billingService._request("addMoney");
		request.set_return_type(orb.get_primitive_tc(TCKind.tk_void));
		Any any = request.add_in_arg();
		any.insert_string(card);
		Any any2 = request.add_in_arg();
		any2.insert_double(money);
		request.invoke();
		Exception exception = request.env().exception();
		if(exception instanceof UnknownUserException) {
			UnknownUserException userEx = (UnknownUserException) exception;
		}
				
	}
	
	void testSubMoney(String card, Double money) {
		Request request = billingService._request("subMoney");
		request.set_return_type(orb.get_primitive_tc(TCKind.tk_void));
		Any any = request.add_in_arg();
		any.insert_string(card);
		Any any2 = request.add_in_arg();
		any2.insert_double(money);;
		request.invoke();
		Exception exception = request.env().exception();
		if(exception instanceof UnknownUserException) {
			UnknownUserException userEx = (UnknownUserException) exception;
		}
				
	}
	
	double testGetCardBalance(String card) {
		Request request = billingService._request("getCardBalance");
		request.set_return_type(orb.get_primitive_tc(TCKind.tk_double));
		Any any = request.add_in_arg();
		any.insert_string(card);
		request.invoke();
		Exception exception = request.env().exception();
		if(exception instanceof UnknownUserException) {
			UnknownUserException userEx = (UnknownUserException) exception;
		}
		double result = request.return_value().extract_double();
		System.out.println("Balance= " + result);
		return result;
	}
	

}
