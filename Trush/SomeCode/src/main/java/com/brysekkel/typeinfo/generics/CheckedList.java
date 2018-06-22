package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CheckedList {
	
	private static class Pet {}
	private static class Dog extends Pet {}
	private static class Cat extends Pet {}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void oldStylemethod(List probablyDogs) {
		probablyDogs.add(new Cat());
		System.out.println("oldStylemethod added Cat to Dog list");
	}
	
	public static void main(String[] args) {
		List<Dog> dogs1 = new ArrayList<>();
		oldStylemethod(dogs1);//принимает Cat без возражений
		
		List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
		try {
			oldStylemethod(dogs2);//Возбуждает исключение
		} catch (Exception e) {
			System.out.println(e);
		}
		
		List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
		pets.add(new Dog());
		System.out.println("Added dog to pet list");
		pets.add(new Cat());
		System.out.println("Added cat to pet list");
	}

}
