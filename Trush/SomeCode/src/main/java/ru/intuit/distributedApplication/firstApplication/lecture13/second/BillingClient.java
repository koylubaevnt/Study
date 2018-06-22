package ru.intuit.distributedApplication.firstApplication.lecture13.second;

import java.util.Date;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

public class BillingClient {

	@Resource(mappedName="jms/ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	
	@Resource(mappedName="jms/Queue")
	private static Queue queue;
	/*
	 * C:\Java\Tools\SDK\JDK\jdk1.7.0_79\bin\javac -classpath .;C:\Java\Tools\maven-repository\javax\javaee-api\7.0\javaee-api-7.0.jar; -d C:\Java\Test\build *.java
	 * 
	 * cd C:\Java\Test\build
	 * 
	 * Изучить испольхзование JAR (т.к. такие команды не смогли помочь добавить файлы...):
	 * jar -cvfm server.jar manifest2.mf -C ./build . 
	 * jar -cvfm client.jar manifest.mf -C ./build .
	 * 
	 * C:\Java\Tools\Servers\Glassfish\glassfish-4.1.1\glassfish\bin\appclient -client client.jar
	 * C:\Java\Tools\Servers\Glassfish\glassfish-4.1.1\glassfish\bin\appclient -client server.jar
	 */
	
	public static void main(String[] args) {
		try (Connection connection = connectionFactory.createConnection()) {
			Destination destination = queue;
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			ObjectMessage message = session.createObjectMessage();
			//Card card = new Card("Piter", LocalDate.now(), "1", 0.0);
			Card card = new Card("Piter", new Date(), "1", 0.0);
			message.setObject(card);
			System.out.println("Sending message: " + message.getObject());
			producer.send(message);
			
			//card = new Card("Stefan", LocalDate.now(), "2", 0.0);
			card = new Card("Stefan", new Date(), "2", 0.0);
			message.setObject(card);
			System.out.println("Sending message: " + message.getObject());
			producer.send(message);
			
			//card = new Card("Nataly", LocalDate.now(), "3", 0.0);
			card = new Card("Nataly", new Date(), "3", 0.0);
			message.setObject(card);
			System.out.println("Sending message: " + message.getObject());
			producer.send(message);
			
			int cnt = 1000;
			for(int i = 0; i < cnt; i++) {
				//CardOperation operation = new CardOperation(String.valueOf((i%3 + 1)), 10 + i, LocalDate.now());
				CardOperation operation = new CardOperation(String.valueOf((i%3 + 1)), 10 + i, new Date());
				message.setObject(operation);
				System.out.println("Sending message: " + message.getObject());
				producer.send(message);
			}
			
			producer.send(session.createMessage());
			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
