package ru.intuit.distributedApplication.firstApplication.lecture9;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.ARG_IN;
import org.omg.CORBA.Any;
import org.omg.CORBA.DynamicImplementation;
import org.omg.CORBA.NVList;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ServerRequest;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.TypeCode;

public class BillingServiceImpl extends DynamicImplementation {

	static String[] myIDs = {"IDL:JavaIDL/DSIBillingService:1.0"};
	ORB orb;
	Map<String, Double> hash;
	
	
	
	public BillingServiceImpl(ORB orb) {
		this.orb = orb;
		this.hash = new HashMap<>();
	}

	@Override
	public void invoke(ServerRequest request) {
		System.out.println("DSI: invoke called, op = " + request.op_name());
		try{
			if(request.op_name().equals("addNewCard")) {
				NVList nvList = orb.create_list(0);
				Any any = orb.create_any();
				any.insert_string("");
				nvList.add_value("arg1", any, ARG_IN.value);
				Any any2 = orb.create_any();
				any2.insert_string("");
				nvList.add_value("arg2", any2, ARG_IN.value);
				request.params(nvList);
				addNewCard(nvList.item(0).value().extract_string(),nvList.item(1).value().extract_string());
				TypeCode resultTC = orb.get_primitive_tc(TCKind.tk_void);
				Any anyResult = orb.create_any();
				anyResult.type(resultTC);
				request.result(anyResult);
			} else if(request.op_name().equals("addMoney")) {
				NVList nvList = orb.create_list(0);
				Any any = orb.create_any();
				any.insert_string("");
				nvList.add_value("arg1", any, ARG_IN.value);
				Any any2 = orb.create_any();
				any2.insert_double(0.0);
				nvList.add_value("arg2", any2, ARG_IN.value);
				request.params(nvList);
				System.out.println("DSI: arg1= " + nvList.item(0).value().extract_string() + 
						", arg2= " + nvList.item(1).value().extract_double());
				addMoney(nvList.item(0).value().extract_string(),nvList.item(1).value().extract_double());
				TypeCode resultTC = orb.get_primitive_tc(TCKind.tk_void);
				Any anyResult = orb.create_any();
				anyResult.type(resultTC);
				request.result(anyResult);
			} else if(request.op_name().equals("subMoney")) {
				NVList nvList = orb.create_list(0);
				Any any = orb.create_any();
				any.insert_string("");
				nvList.add_value("arg1", any, ARG_IN.value);
				Any any2 = orb.create_any();
				any2.insert_double(0.0);
				nvList.add_value("arg2", any2, ARG_IN.value);
				request.params(nvList);
				System.out.println("DSI: arg1= " + nvList.item(0).value().extract_string() + 
						", arg2= " + nvList.item(1).value().extract_double());
				subMoney(nvList.item(0).value().extract_string(),nvList.item(1).value().extract_double());
				TypeCode resultTC = orb.get_primitive_tc(TCKind.tk_void);
				Any anyResult = orb.create_any();
				anyResult.type(resultTC);
				request.result(anyResult);
			} else if(request.op_name().equals("getCardBalance")) {
				NVList nvList = orb.create_list(0);
				Any any = orb.create_any();
				any.insert_string("");
				nvList.add_value("arg1", any, ARG_IN.value);
				request.params(nvList);
				double d = getCardBalance(nvList.item(0).value().extract_string());
				Any anyResult = orb.create_any();
				anyResult.insert_double(d);
				request.result(anyResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DSI: Exception " + e);
		}
	}

	private void addNewCard(String extract_string, String extract_string2) {
		hash.put(extract_string2, new Double(0.0));
	}

	private void addMoney(String extract_string, double extract_double) {
		System.out.println("DII: addMoney " + extract_string + " - " + extract_double);
		synchronized (hash) {
			Double d = hash.get(extract_string);
			System.out.println("DII: d = " + d);
			if(d != null) {
				hash.put(extract_string, d + extract_double); 
			}
		}
	}

	private void subMoney(String extract_string, double extract_double) {
		System.out.println("DII: addMoney " + extract_string + " - " + extract_double);
		synchronized (hash) {
			Double d = hash.get(extract_string);
			System.out.println("DII: d = " + d);
			if(d != null) {
				hash.put(extract_string, d - extract_double); 
			}
		}
	}

	private double getCardBalance(String extract_string) {
		System.out.println("DII: getCardBalance " + extract_string);
		Double d = hash.get(extract_string);
		System.out.println("DII: d = " + d);
		return (d != null) ? d : 0.0;
	}

	@Override
	public String[] _ids() {
		return myIDs;
	}
}
