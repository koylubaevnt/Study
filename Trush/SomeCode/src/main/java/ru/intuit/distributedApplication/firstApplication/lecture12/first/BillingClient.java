package ru.intuit.distributedApplication.firstApplication.lecture12.first;

public class BillingClient {
/*
	@WebServiceRef(wsdlLocation="http://localhost:8080/billingservice/billing?wsdl")
	static BillingService service;
	
	public static void main(String[] args) {
		try {
			BillingClient client = new BillingClient();
			client.startTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void startTest() {
		try {
			Billing port = service.getBillingPort();
			List<Card> list = new ArrayList<>();
			
			Card card = new Card();
			card.setPerson("Piter");
			XMLGregorianCalendar date = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			date.setYear(2006);
			date.setMonth(11);
			date.setDay(5);
			card.setCreateDate(date);
			card.setCard("1");
			card.setBalance(0.0);
			list.add(card);
			
			card = new Card();
			card.setPerson("Stefan");
			card.setCreateDate(date);
			card.setCard("2");
			card.setBalance(0.0);
			list.add(card);
			
			card = new Card();
			card.setPerson("Nataly");
			card.setCreateDate(date);
			card.setCard("3");
			card.setBalance(0.0);
			list.add(card);
			
			port.addNewCard(list);
			
			int cnt = 30000;
			List<CardOperation> operations = new ArrayList<>(cnt);
			CardOperation cardOperation;
			for(int i = 0; i < cnt; i++) {
				cardOperation = new CardOperation();
				cardOperation.setCard(String.valueOf(i % 3 + 1));
				cardOperation.setAmount(i % 3 + 1);
				cardOperation.setOperationDate(date);
				operations.add(cardOperation);
			}
			
			port.processOperation(cardOperations);
			
			printCard(port.getCard("1"));
			printCard(port.getCard("2"));
			printCard(port.getCard("3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void printCard(Card card) {
		System.out.println(card.getCard() 
				+ "\t" + card.getPerson() 
				+ "\t" +card.getCreateDate()
				+ "\t" + card.getBalance());
	}
*/
	
}
