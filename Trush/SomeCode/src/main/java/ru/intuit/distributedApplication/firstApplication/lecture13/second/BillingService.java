package ru.intuit.distributedApplication.firstApplication.lecture13.second;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

public class BillingService {

	@Resource(mappedName="jms/ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	
	@Resource(mappedName="jms/Queue")
	private static Queue queue;
	
	Map<String, Card> hash = new ConcurrentHashMap<>();
	
	public static void main(String[] args) {
		try(Connection connection = connectionFactory.createConnection()) {
			Destination destination = queue;
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer consumer = session.createConsumer(destination);
			ObjectListener listener = new ObjectListener(new BillingService());
			consumer.setMessageListener(listener);
			connection.start();
			System.out.println("To end program, type ! or q, then <return>");
			InputStreamReader inputStreamReader = new InputStreamReader(System.in);
			char answer = '\0';
			while((answer != 'q') && (answer !='Q')) {
				try {
					answer = (char) inputStreamReader.read();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
	
	public void addNewCard(Card card) {
		hash.put(card.getCard(), card);
	}

	public void performOperation(CardOperation operation) {
		Card card = hash.get(operation.getCard());
		if(card == null) {
			return;
		}
		card.setBalance(card.getBalance() + operation.getAmount());
		hash.put(operation.getCard(), card);
	}
	
	public void printCards() {
		for (Entry<String, Card> element : hash.entrySet()) {
			System.out.println(element);
		}
	}

}
