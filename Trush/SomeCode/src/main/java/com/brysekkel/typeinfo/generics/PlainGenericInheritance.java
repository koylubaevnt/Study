package com.brysekkel.typeinfo.generics;

class GenericSetter<T> {
	void set(T arg) {
		System.out.println("GenericSetter");
	}
	void new1() {}
}

class DerivedGS extends GenericSetter<Base> {
	void set(Derived arg) {
		System.out.println("DerivedGS");
	}
}

public class PlainGenericInheritance {

	public static void main(String[] args) {
		Base base = new Base();
		Derived derived = new Derived();
		DerivedGS dgs = new DerivedGS();
		dgs.set(derived);
		dgs.set(base);
	}

}
