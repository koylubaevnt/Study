package com.brysekkel.typeinfo.generics;

class SelfBounded2<T extends SelfBounded2<T>> {
	T element;
	SelfBounded2<T> set(T arg) {
		element = arg;
		return this;
	}
	T get() {
		return element;
	}
}

class A3 extends SelfBounded2<A3> {}

public class SelfBoundingMethods {

	static <T extends SelfBounded2<T>> T f (T arg) {
		return arg.set(arg).get();// Можно исопльзовать методы класса SelfBounded2 
	}
	
	public static void main(String[] args) {
		A3 a = f(new A3());

	}

}
