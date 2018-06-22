package ru.intuit.distributedApplication.firstApplication.lecture13.first;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSServer {

	@Resource(mappedName="jms/Example1ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	
	@Resource(mappedName="jms/Example1Queue")
	private static Queue queue;
	
	public static void main(String[] args) {
		String destinationType = null;
		Session session = null;
		Destination destination = queue;
		MessageConsumer consumer = null;
		TextMessage textMessage = null;
		
		try(Connection connection = connectionFactory.createConnection()) {
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			consumer = session.createConsumer(destination);
			connection.start();
			
			while (true) {
				Message message = consumer.receive(1);
				if (message != null) {
					if(message instanceof TextMessage) {
						textMessage = (TextMessage) message;
						System.out.println("Reading message: " + textMessage.getText());
					}
				}
			}
			
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
