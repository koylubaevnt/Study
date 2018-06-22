package ru.intuit.distributedApplication.firstApplication.lecture13.first;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

public class JMSClient {

	//@Resource(mappedName="jms/Example1ConnectionFactory")
	@Resource(mappedName="jms/Example1ConnectionFactory")
	private static ConnectionFactory connectionFactory;
	
	@Resource(mappedName="jms/Example1Queue")
	private static Queue queue;
	
	/*
	 * //Создание ConnectionFactory  для JMS ресурса 
	 * asadmin --user admin --host localhost --port 4848 create-jms-resource --restype javax.jms.ConnectionFactory --property Name=jms/Example1ConnectionFactory jms/Example1ConnectionFactory
	 * 
	 * // Создание пунктов назначения
	 * asadmin --user admin --host localhost --port 4848 create-jms-resource --restype javax.jms.Queue --enabled=true --property Name=jms/Example1Queue jms/Example1Queue
	 */
	public static void main(String[] args) {
		
		Destination destination = queue;
		try (Connection connection = connectionFactory.createConnection()) {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage();
			InputStreamReader inputStreamReader = new InputStreamReader(System.in);
			char c = 'n';
			int i = 0;
			while(!((c == 'q') || (c == 'Q'))) {
				try {
					c = (char) inputStreamReader.read();
					message.setText("This is message " + (i + 1));
					System.out.println("Sending message: " + message.getText());
					producer.send(message);
					i++;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
