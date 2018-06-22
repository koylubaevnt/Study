package com.brysekkel.typeinfo.generics;


class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

public class CovariantArrays {

	public static void main(String[] args) {
		Fruit[] fruits = new Apple[10];
		fruits[0] = new Apple();
		fruits[1] = new Jonathan();
		//тип времени выполнения Apple, a не Fruit, Orange
		try {
			//Компилятор не ругается
			fruits[2] = new Fruit();
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			//Компилятор не ругается
			fruits[3] = new Orange();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
