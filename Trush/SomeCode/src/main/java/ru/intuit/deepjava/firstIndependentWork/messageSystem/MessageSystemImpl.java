package ru.intuit.deepjava.firstIndependentWork.messageSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import ru.intuit.deepjava.firstIndependentWork.base.Abonent;
import ru.intuit.deepjava.firstIndependentWork.base.Address;
import ru.intuit.deepjava.firstIndependentWork.base.AddressService;
import ru.intuit.deepjava.firstIndependentWork.base.Message;
import ru.intuit.deepjava.firstIndependentWork.base.MessageSystem;

public class MessageSystemImpl implements MessageSystem {
	
	final private int MAX_QUEUE_SIZE= 1000;
	
	private Map<Address, ArrayBlockingQueue<Message>> messages
		= new HashMap<>();
	private AddressService addressService = new AddressServiceImpl();
	
	@Override
	public void addService(Abonent abonent) {
		addressService.setAddresses(abonent);
		messages.put(abonent.getAddress(), new ArrayBlockingQueue<>(MAX_QUEUE_SIZE));
	}
	
	@Override
	public void sendMessage(Message message) {
		Queue<Message> messageQueue = messages.get(message.getTo());
		messageQueue.add(message);
	}

	@Override
	public void execForAbonent(Abonent abonent) {
		Queue<Message> messageQueue = messages.get(abonent.getAddress());
		if(messageQueue == null) {
			return;
		}
		while(!messageQueue.isEmpty()) {
			Message message = messageQueue.poll();
			message.exec(abonent);
		}
	}
	
	@Override
	public AddressService getAddressService() {
		return addressService;
	}
	
}
