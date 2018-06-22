package com.koylubaevnt.own.others;

import java.util.ArrayList;
import java.util.List;

public class GenericsDemo {

	static class Animal {
		void feed() {
			System.out.println("Animal feed()");
		}
	}

	static class Pet extends Animal {
		void call() {
			System.out.println("Pet call()");
		}
	}
	
	static class Cat extends Pet {
		void mew() {
			System.out.println("Cat mew()");
		}
	}
	
	static class Dog extends Pet {
		void bark() {
			System.out.println("Dog");
		}
	}
	
	static <T> void copy(List<? super T> dest, List<? extends T> src) {
		for(T e : src) {
			dest.add(e);
		}
	}
	/*
	// Несоотвествие типов, если передавать List<Dog>, list<Cat>
	static void callPets(List<Pet> pets) {
		for(Pet pet : pets) {
			pet.call();
		}
	}
	*/
	/*
	//Второй рабочий вариант (producer)
	static void callPets(List<? extends Pet> pets) {
		for(Pet pet : pets) {
			pet.call();
		}
	}
	*/
	/*
	// Несоотвествие типов, если передавать List<Animal>
	static void fillPets(List<Pet> pets) {
		pets.add(new Dog());
		pets.add(new Cat());
	}
	*/
	
	//Коллекция pets - поставщик данных (producer) -> EXTENDS [операция чтения]
		//Коллекция поставляет данные
		//Поставщик может работать с типом Т и его наследниками (приведенными к типу Т)
		static <T extends Pet> void callPets(List<T> pets) {
			for(T pet : pets) {
				pet.call();
			}
		}
	//Коллекция pets - потребитель данных (consumer) -> SUPER [операция вставки]
	//Коллекция потребляет данные
		//Потребитель может принимать тип Т и его предков (приведенными к Object)
	static void fillPets(List<? super Pet> pets) {
		pets.add(new Dog());
		pets.add(new Cat());
	}
	
	public static void main(String[] args) {
		/*
		List<String> strs = new ArrayList<>();
		List<Object> objs = strs;// Несовметимые типы
		objs.add(new Integer(1));
		for(String str : strs) {
			str.toUpperCase(); // Ошибка, т.к. положили new Integer(1)!!! Если бы можно было сделать List<Object> objs = strs; 
		}
		List lst = strs;
		*/
		List<Cat> cats = new ArrayList<>();
		cats.add(new Cat());
		cats.add(new Cat());
		
		List<? extends Pet> pets = cats;
		
		List<Dog> dogs = new ArrayList<>();
		dogs.add(new Dog());
		dogs.add(new Dog());
		
		callPets(cats); // Несовместимые типы - ошибка компиляции
		callPets(dogs);
		
		List<Animal> animals = new ArrayList<>();
		// Несовместимые типы
		fillPets(animals);
	}
	
}
