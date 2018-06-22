package ru.intuit.distributedApplication.firstApplication.lecture6.simple;

import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.ORB;

public class BillingServiceImpl extends BillingServicePOA {

	private ORB orb;
	private Map<String, Double> hash = new HashMap<>();
	
	public void setORB(ORB orb) {
		this.orb = orb;
	}
	
	@Override
	public void addNewCard(String personName, String card) {
		hash.put(card, Double.valueOf(0));
	}

	@Override
	public void addMoney(String card, double money) {
		Double d = hash.get(card);
		if (d != null) {
			hash.put(card, d + money);
		}
	}

	@Override
	public void subMoney(String card, double money) {
		Double d = hash.get(card);
		if (d != null) {
			hash.put(card, d - money);
		}
	}

	@Override
	public double getCardBalance(String card) {
		Double d = hash.get(card);
		if (d == null) {
			d = 0.0;
		}
		return d;
	}

}
