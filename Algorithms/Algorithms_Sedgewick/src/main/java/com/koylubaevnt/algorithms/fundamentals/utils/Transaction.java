package com.koylubaevnt.algorithms.fundamentals.utils;

import java.util.Date;

public class Transaction implements Comparable<Transaction> {

	private String who;
	private Date when;
	private double amount;

	public Transaction(String transaction) {
		String[] a = transaction.split("\\s+");
		who = a[0];
		when = new Date(a[1]);
		amount = Double.parseDouble(a[2]);
		if (Double.isNaN(amount) || Double.isInfinite(amount))
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
	}

	public Transaction(String who, Date when, double amount) {
		if (Double.isNaN(amount) || Double.isInfinite(amount))
			throw new IllegalArgumentException("Amount cannot be NaN or infinite");
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		int hash = 1;
		hash = 31 * hash + who.hashCode();
		hash = 31 * hash + when.hashCode();
		hash = 31 * hash + ((Double) amount).hashCode();
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() != this.getClass())
			return false;
		Transaction that = (Transaction) obj;
		return (this.amount == that.amount) && (this.who.equals(that.who)) && (this.when.equals(that.when));
	}

	@Override
	public String toString() {
		return String.format("%-10s %10s %8.2f", who, when, amount);
	}

	public int compareTo(Transaction o) {
		return Double.compare(this.amount, amount);
	}

}
