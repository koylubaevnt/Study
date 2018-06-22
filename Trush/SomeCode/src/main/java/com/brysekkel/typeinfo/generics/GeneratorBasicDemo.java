package com.brysekkel.typeinfo.generics;

public class GeneratorBasicDemo {

	public static void main(String[] args) {
		Generator<CounterObject> gen = GeneratorBasic.create(CounterObject.class);
		for(int i = 0; i < 5; i++) {
			System.out.println(gen.next());
		}

	}

}
