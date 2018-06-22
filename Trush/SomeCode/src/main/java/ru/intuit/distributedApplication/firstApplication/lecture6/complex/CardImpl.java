package ru.intuit.distributedApplication.firstApplication.lecture6.complex;

public class CardImpl extends CardPOA {

	private String personName;
	private double balance;
	
	public CardImpl(String personName, double balance) {
		this.personName = personName;
		this.balance = balance;
	}

	@Override
	public void addMoney(double money) {
		balance += money;

	}

	@Override
	public void subMoney(double money) {
		balance -= money;

	}

	@Override
	public double getBalance() {
		return balance;
	}

}
