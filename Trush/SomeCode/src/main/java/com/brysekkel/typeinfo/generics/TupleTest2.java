package com.brysekkel.typeinfo.generics;

import static com.brysekkel.typeinfo.generics.Tuple.tuple;

public class TupleTest2 {

	static TupleTwo<String, Integer> f() {
		return tuple("hi", 47);
	}
	
	static TupleTwo f2() {
		return tuple("hi", 47);
	}
	
	static TupleThree<Amphibian, String, Integer> g() {
		return  tuple(new Amphibian(), "hi", 47);
	}
	
	static TupleFour<Vehicle, Amphibian, String, Integer> h() {
		return tuple(new Vehicle(), new Amphibian(), "hi", 47);
	}
	
	static TupleFive<Vehicle, Amphibian, String, Integer, Double> k() {
		return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
	}
	
	public static void main(String[] args) {
		TupleTwo<String, Integer> ttsi = f();
		System.out.println(ttsi);
		System.out.println(f2());
		System.out.println(g());
		System.out.println(h());
		System.out.println(k());
	}

}
