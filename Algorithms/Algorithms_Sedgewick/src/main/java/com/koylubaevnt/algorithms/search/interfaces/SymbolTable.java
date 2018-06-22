package com.koylubaevnt.algorithms.search.interfaces;

public abstract class SymbolTable<Key extends Comparable<Key>, Value> implements ISymbolTable<Key, Value> {
	
	public void delete(Key key) {
		put(key, null);
	}
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	};
	
}
