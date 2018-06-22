package com.brysekkel.typeinfo.generics;

public class TupleFour<A, B, C, D> extends TupleThree<A, B, C> {

	public final D four;
	
	public TupleFour(A a, B b, C c, D d) {
		super(a, b, c);
		four = d;
	}

	@Override
	public String toString() {
		return "FourTuple [first=" + first + ", second=" + second + ", third=" + third + ", four=" + four + "]";
	}
	
	
	
}
