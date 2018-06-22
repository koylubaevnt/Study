package com.koylubaevnt.own.others;

import java.util.ArrayList;
import java.util.List;

public class Button {

	//private ClickListener[] listeners = new ClickListener[10];
	private List<ClickListener> listeners = new ArrayList<>();
	//private int count = 0;
	
	public void addListener(ClickListener listener) {
		//listeners[count++] = listener;
		listeners.add(listener);
	}
	
	public void removeListener(ClickListener listener) {
		//listeners[count++] = listener;
		listeners.remove(listener);
	}
	
	public void click() {
		for(ClickListener listener : listeners) {
			listener.onClick();
		}
		/*
		for(int i = 0 ; i < count; i++) {
			listeners[i].onClick();
		}
		*/
	}
	
}
