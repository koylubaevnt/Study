package com.brysekkel.typeinfo.generics;

public class TupleFive<A, B, C, D, E> extends TupleFour<A, B, C, D> {

	public final E five;
	
	public TupleFive(A a, B b, C c, D d, E e) {
		super(a, b, c, d);
		five = e;
	}

	@Override
	public String toString() {
		return "FiveTuple [first=" + first + ", second=" + second + ", third=" + third + ", four=" + four + ", five=" + five + "]";
	}
	
	
	
}
