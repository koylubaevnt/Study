package ru.intuit.distributedApplication.firstApplication.lecture12.second;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Billing {

	private Map<String, Card> hash;
	
	//Требования, предъявляемые к Web -сервису -> определен конструктор по умолчанию (без параметров).
	public Billing() {
		hash = new ConcurrentHashMap();
	}
	
	@WebMethod
	public void addNewCard(Card[] cards) {
		for(int i = 0; i < cards.length; i++) {
			hash.put(cards[i].getCard(), cards[i]);
		}
	}
	
	@WebMethod
	public void addMoney(String card, double money) {
		Card c = hash.get(card);
		if(c == null) {
			System.out.println("Bad Card number");
			return;
		}
		c.setBalance(c.getBalance() + money);
		hash.put(card, c);
	}
	
	@WebMethod
	public void processOperation(CardOperation[] cardOperations) {
		for(int i = 0; i < cardOperations.length; i++) {
			Card card = hash.get(cardOperations[i].getCard());
			if(card == null) {
				System.out.println("Bad Card number");
				return;
			}
			card.setBalance(card.getBalance() + cardOperations[i].getAmount());
			hash.put(cardOperations[i].getCard(), card);
		}
	}
	
	@WebMethod
	public Card getCard(String card) {
		return hash.get(card);
	}
}
