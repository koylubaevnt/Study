package ru.intuit.distributedApplication.firstApplication.lecture4;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <code>Card</code>- представление карты
 * 
 * @author KojlubaevNT
 * @version 1.0.0
 *  
 */
public class Card implements Serializable {
	private static final long serialVersionUID = -6875202029219174584L;
	
	private String person;
	private transient LocalDate createDate;
	private String card;
	private double balance;
	
	public Card(String person, LocalDate createDate, String card, double balance) {
		this.person = person;
		this.createDate = createDate;
		this.card = card;
		this.balance = balance;
	}
	
	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Card [person=" + person + ", createDate=" + createDate + ", card=" + card + ", balance=" + balance
				+ "]";
	}
	
}
