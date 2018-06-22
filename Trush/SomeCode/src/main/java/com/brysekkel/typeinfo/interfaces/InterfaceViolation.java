package com.brysekkel.typeinfo.interfaces;

class B implements A {

	@Override
	public void f() {
		System.out.println("B.f");
	}

	public void b() {
		System.out.println("B.b");
	}
}

public class InterfaceViolation {

	public static void main(String[] args) {
		A a = new B();
		a.f();
		//a.g(); 
		System.out.println(a.getClass().getName());
		if(a instanceof B) {
			((B) a).b();
		}
	}

}
