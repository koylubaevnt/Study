package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;

public class TupleList<A1, B1, C1, D1> extends ArrayList<TupleFour<A1, B1, C1, D1>> {

	public static void main(String[] args) {
		//TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<Vehicle, Amphibian, String, Integer>();
		TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<>();
		tl.add(TupleTest.h());
		tl.add(TupleTest.h());
		for(TupleFour<Vehicle, Amphibian, String, Integer> i : tl) {
			System.out.println(i);
		}

	}

}
