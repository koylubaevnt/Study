package com.brysekkel.typeinfo.generics;

public class TupleThree<A, B, C> extends TupleTwo<A, B> {

	public final C third;
	
	public TupleThree(A a, B b, C c) {
		super(a, b);
		third = c;
	}

	@Override
	public String toString() {
		return "ThreeTuple [first=" + first + ", second=" + second + ", third=" + third + "]";
	}
	
	
	
}
