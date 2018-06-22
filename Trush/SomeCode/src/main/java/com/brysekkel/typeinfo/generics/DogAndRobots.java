package com.brysekkel.typeinfo.generics;

class Dog {}

class PerformingDog extends Dog implements Performs {
	@Override
	public void speak() {
		System.out.println("Woof!");		
	}

	@Override
	public void sit() {
		System.out.println("Sitting");
	}
	public void reproduce() {}
}

class Robot implements Performs {

	@Override
	public void speak() {
		System.out.println("Click!");
	}

	@Override
	public void sit() {
		System.out.println("Clank!");
	}
	
	public void oilChange() {}
}

//Для работы в данном случае метода perform обобщения не нужны .
//Достаточно просто передать объект Perform
class Communicate {
	public static<T extends Performs> void perform(T perform) {
		perform.speak();
		perform.sit();
	}
}

public class DogAndRobots {

	public static void main(String[] args) {
		PerformingDog d = new PerformingDog();
		Robot r = new Robot();
		Communicate.perform(d);
		Communicate.perform(r);

	}

}
