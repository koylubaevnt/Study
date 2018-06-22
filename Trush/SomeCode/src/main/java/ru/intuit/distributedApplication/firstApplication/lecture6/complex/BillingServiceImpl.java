package ru.intuit.distributedApplication.firstApplication.lecture6.complex;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BillingServiceImpl extends BillingServicePOA {

	private Map<String, Card> cards = new HashMap<>();
	private Random random = new Random();
	
	@Override
	public Card getCard(String card) {
		return cards.get(card);
	}

	@Override
	public Card addNewCard(String personName, String cardCode) {
		Card card = cards.get(cardCode);
		if(card == null) {
			CardImpl cardServant = new CardImpl(personName, 0.0);
			try {
				card = CardHelper.narrow(_default_POA().servant_to_reference(cardServant));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Created " + cardCode + "'s account: " + card);
			cards.put(cardCode, card);
		}
		return card;
	}

}
