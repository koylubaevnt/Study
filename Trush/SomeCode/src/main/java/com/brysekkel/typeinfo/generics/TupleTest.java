package com.brysekkel.typeinfo.generics;

//class Amphibian {}
//class Vehicle {}

public class TupleTest {

	static TupleTwo<String, Integer> f() {
		return new TupleTwo<>("hi", 47);
	}
	
	static TupleThree<Amphibian, String, Integer> g() {
		return new TupleThree<>(new Amphibian(), "hi", 47);
	}
	
	static TupleFour<Vehicle, Amphibian, String, Integer> h() {
		return new TupleFour<>(new Vehicle(), new Amphibian(), "hi", 47);
	}
	
	static TupleFive<Vehicle, Amphibian, String, Integer, Double> k() {
		return new TupleFive<>(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
	}
	
	public static void main(String[] args) {
		TupleTwo<String, Integer> ttsi = f();
		System.out.println(ttsi);
		/*
		//Ошибка компиляции
		ttsi.first = "there";
		ttsi.second = 2;
		*/
		System.out.println(g());
		System.out.println(h());
		System.out.println(k());
	}

}
