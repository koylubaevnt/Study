package ru.intuit.deepjava.firstIndependentWork.utils.events;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class EventSource {

	private List<EventListener> listeners = new LinkedList<>();

	public void addListener(EventListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(EventListener listener) {
		listeners.remove(listener);
	}
	
	public void fireEvent(EventObject event) {
		for(EventListener listener : listeners) {
			listener.handle(event);
		}
	}
	
}
