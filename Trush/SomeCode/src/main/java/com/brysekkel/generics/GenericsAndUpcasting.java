package com.brysekkel.generics;

import java.util.ArrayList;

public class GenericsAndUpcasting {

	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<>();
		apples.add(new GrannySmith());
		apples.add(new Gala());
		apples.add(new Fuji());
		apples.add(new Braeburn());
		for(Apple c : apples) {
			System.out.println(c);
		}
		
	}

}

class Apple {
	private static long counter;
	private final long id = counter++;
	public long id() {return id;}
}

class GrannySmith extends Apple {}
class Gala extends Apple {}
class Fuji extends Apple {}
class Braeburn extends Apple {}