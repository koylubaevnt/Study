package com.brysekkel.typeinfo.containers;

import java.util.WeakHashMap;

class Element {
	private String ident;
	public Element(String id) {
		ident = id;
	}
	
	@Override
	public int hashCode() {
		return ident.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Element && ident.equals(((Element)obj).ident);
	}
	
	@Override
	public String toString() {
		return ident;
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
	}
}

class Key extends Element {
	public Key(String id) {
		super(id);
	}
}

class Value extends Element {
	public Value(String id) {
		super(id);
	}
}
public class CanonicalMapping {

	public static void main(String[] args) {
		int size = 1000;
		if(args.length > 0) {
			size = new Integer(args[0]);
		}
		Key[] keys = new Key[size];
		WeakHashMap<Key, Value> map =
				new WeakHashMap<>();
		for(int i = 0; i < size; i++) {
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if(i % 3 == 0) {
				keys[i] = k;
			}
			map.put(k, v);
		}
		System.gc();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
