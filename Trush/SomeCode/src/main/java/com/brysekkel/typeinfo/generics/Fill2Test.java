package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface Addable<T> {
	
	void add(T t);
	
}

class Fill2 {
	
	//Версия с маркером
	public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
		for(int i = 0; i < size; i++) {
			try {
				addable.add(classToken.newInstance());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	//Версия с генератором
	public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
		for(int i = 0; i < size; i++) {
			addable.add(generator.next());
		}
	}
}

class AddableCollectionAdapter<T> implements Addable<T> {

	private Collection<T> collection;
	
	public AddableCollectionAdapter(Collection<T> collection) {
		this.collection = collection;
	}
	
	@Override
	public void add(T t) {
		collection.add(t);
	}
	
}

class Adapter {
	public static <T> Addable<T> collectionAdapter(Collection<T> collection) {
		return new AddableCollectionAdapter<>(collection);
	}
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
	
	@Override
	public void add(T t) {
		super.add(t);
	}
}

public class Fill2Test {

	public static void main(String[] args) {
		List<Coffee> carrier = new ArrayList<>();
		Fill2.fill(new AddableCollectionAdapter<Coffee>(carrier), 
				Coffee.class, 3);
		Fill2.fill(Adapter.collectionAdapter(carrier), 
				CoffeeLatte.class, 2);
		for(Coffee coffee : carrier) {
			System.out.println(coffee);
		}
		System.out.println("-------------------");
		AddableSimpleQueue<Coffee> coffeeQueue =
				new AddableSimpleQueue<>();
		Fill2.fill(coffeeQueue, CoffeeMocha.class, 4);
		Fill2.fill(coffeeQueue, CoffeeLatte.class, 1);
		for(Coffee coffee : coffeeQueue) {
			System.out.println(coffee);
		}
	}

}
