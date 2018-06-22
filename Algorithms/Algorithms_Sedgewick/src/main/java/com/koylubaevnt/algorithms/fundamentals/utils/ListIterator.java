package com.koylubaevnt.algorithms.fundamentals.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.koylubaevnt.algorithms.fundamentals.Node;

public class ListIterator<Item> implements Iterator<Item> {
	private Node<Item> current;
	
	public ListIterator(Node<Item> node) {
		current = node;
	}

	public boolean hasNext() {
		return current != null;
	}

	public Item next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		Item item = current.getItem();
		current = current.getNext(); 
		return item;
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
