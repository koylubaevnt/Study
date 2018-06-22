package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericReading {

	static <T> T readExact(List<T> list, T item) {
		list.add(item);
		return list.get(0);
	}
	
	static List<Apple> apples = new ArrayList<>();//Arrays.asList(new Apple());
	static List<Fruit> fruits = new ArrayList<>();//Arrays.asList(new Apple());
	
	static void f1() {
		Apple a = readExact(apples, new Apple());
		Fruit f = readExact(fruits, new Apple());
		f = readExact(apples, new Apple());
	}
	
	//Для класса тип устанавливается при создании экземпляра класса
	static class Reader<T> {
		T readExact(List<T> list) {
			return list.get(0);
		}
	}
	
	static void f2() {
		Reader<Fruit> fruitReader = new Reader<>();
		Fruit f = fruitReader.readExact(fruits);
		//readExact(List<Fruits>) не может применятьс к List<Apple>
		//Fruit a = fruitReader.readExact(apples);
	}
	
	static class CovariantReader<T> {
		T readExact(List<? extends T> list) {
			return list.get(0);
		}
	}
	
	static void f3() {
		CovariantReader<Fruit> fruitReader = new CovariantReader<>();
		Fruit f = fruitReader.readExact(fruits);
		Fruit a = fruitReader.readExact(apples);
	}
	
	public static void main(String[] args) {
		f1();
		f2();
		f3();
	}

}
