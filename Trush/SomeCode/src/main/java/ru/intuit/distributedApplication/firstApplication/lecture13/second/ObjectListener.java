package ru.intuit.distributedApplication.firstApplication.lecture13.second;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class ObjectListener implements MessageListener {

	private BillingService billingService;
	
	public ObjectListener(BillingService billingService) {
		this.billingService = billingService;
	}

	@Override
	public void onMessage(Message message) {
		ObjectMessage objectMessage = null;
		
		try {
			if(message instanceof ObjectMessage) {
				objectMessage = (ObjectMessage) message;
				Object object = objectMessage.getObject();
				if(object instanceof Card) {
					System.out.println("addNewCard: " + (Card) object);
					billingService.addNewCard((Card) object);
				} else if(object instanceof CardOperation) {
					System.out.println("performOperation: " + (CardOperation) object);
					billingService.performOperation((CardOperation) object);
				}
			} else {
				System.out.println("Message is not a ObjectMessage");
				billingService.printCards();
			}
		} catch (JMSException e) {
			System.out.println("JMSException in onMessage(): " + e.toString());
		} catch (Throwable t) {
			System.out.println("Exception in onMessage():" + t.getMessage());
		}
		
	}

}
