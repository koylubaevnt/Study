package ru.intuit.distributedApplication.firstApplication.lecture13.second;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>CardOperation</code> - "операция над картой"
 * 
 * @author KojlubaevNT
 * @version 1.0.0
 * 
 */
public class CardOperation implements Serializable {

	private static final long serialVersionUID = 8424577920700831669L;
	
	private String card;
	private double amount;
	//private LocalDate operationDate;
	private Date operationDate;
	
	//Конструктор по умолчанию - это одно из требований, накладываемых используемой технологией.
	public CardOperation() {}
	
	//public CardOperation(String card, double amount, LocalDate operationDate) {
	public CardOperation(String card, double amount, Date operationDate) {
		this.card = card;
		this.amount = amount;
		this.operationDate = operationDate;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	//public LocalDate getOperationDate() {
	public Date getOperationDate() {
		return operationDate;
	}
	//public void setOperationDate(LocalDate operationDate) {
	public void setOperationDate(Date operationDate) {
		this.operationDate = operationDate;
	}

	@Override
	public String toString() {
		return "CardOperation [card=" + card + ", amount=" + amount + ", operationDate=" + operationDate + "]";
	}
	
	
}
