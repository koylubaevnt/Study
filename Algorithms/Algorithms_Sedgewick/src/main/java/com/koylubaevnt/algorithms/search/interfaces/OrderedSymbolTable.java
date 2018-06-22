package com.koylubaevnt.algorithms.search.interfaces;

public abstract class OrderedSymbolTable<Key extends Comparable<Key>, Value> implements IOrderedSymbolTable<Key, Value> {
	
	public boolean contains(Key key) {
		return get(key) != null;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	};
	
	public void deleteMin() {
		delete(min());
	}
	
	public void deleteMax() {
		delete(max());
	}
	
	public int size(Key lo, Key hi) {
		if (hi.compareTo(lo) < 0) {
			return 0;
		} else if (contains(hi)) {
			return rank(hi) - rank(lo) + 1;
		} else {
			return rank(hi) - rank(lo);
		}
			
	}
	
	public Iterable<Key> keys() {
		return keys(min(), max());
	}
	
}
