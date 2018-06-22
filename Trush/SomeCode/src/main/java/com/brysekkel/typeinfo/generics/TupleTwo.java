package com.brysekkel.typeinfo.generics;

public class TupleTwo<A, B> {

	public final A first;	
	public final B second;
	
	public TupleTwo(A a, B b) {
		this.first = a;
		this.second = b;
	}

	@Override
	public String toString() {
		return "TwoTuple [first=" + first + ", second=" + second + "]";
	}
	
	
	
}
