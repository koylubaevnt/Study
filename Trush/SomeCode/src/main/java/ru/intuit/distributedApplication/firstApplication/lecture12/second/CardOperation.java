package ru.intuit.distributedApplication.firstApplication.lecture12.second;

import java.io.Serializable;
import java.time.LocalDate;

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
	private LocalDate operationDate;
	
	//Конструктор по умолчанию - это одно из требований, накладываемых используемой технологией.
	public CardOperation() {}
	
	public CardOperation(String card, double amount, LocalDate operationDate) {
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
	public LocalDate getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(LocalDate operationDate) {
		this.operationDate = operationDate;
	}
	
}
