package com.brysekkel.typeinfo.generics;

public class Tuple {

	public static <A,B> TupleTwo<A, B> tuple(A a, B b) {
		return new TupleTwo<A, B>(a, b);
	}
	
	public static <A,B,C> TupleThree<A,B,C> tuple(A a, B b, C c) {
		return new TupleThree<>(a, b, c);
	}
	
	public static <A,B,C,D> TupleFour<A, B,C,D> tuple(A a, B b, C c, D d) {
		return new TupleFour<>(a, b, c, d);
	}
	
	public static <A,B,C,D,E> TupleFive<A, B,C,D,E> tuple(A a, B b, C c, D d, E e) {
		return new TupleFive<>(a, b, c, d, e);
	}
}
