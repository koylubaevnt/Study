package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericWritting {
	
	static <T> void writeExact(List<T> list, T item) {
		list.add(item);
		list.get(0);
	}
	
	static List<Apple> apples = new ArrayList<Apple>();
	static List<Fruit> fruits = new ArrayList<Fruit>();
	static List<Orange> oranges = new ArrayList<Orange>();
	
	static void f1() {
		writeExact(apples, new Apple());
		//СТРАННОЕ ПОВЕДЕНИЕ МЕТОДА... Видимо как-то компилятор поумнел. Понимает что T это Fruit  в него можно положить Apple
		writeExact(fruits, new Apple());//В книге написано что: Несовместимые типы. На Java 8 отрабатывает
	}
	
	static <T> void writeWithWildcard(List<? super T> list, T item) {
		list.add(item);
	}
	
	static void f2() {
		writeWithWildcard(apples, new Apple());
		writeWithWildcard(fruits, new Apple());
	}
	
	public static void main(String[] args) {
		f1();
		f2();
	}

}
