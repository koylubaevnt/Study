package com.brysekkel.typeinfo.generics;

//Самоограничение требует использовать  класс в отношении наследования следующего вида: class A extends SelfBounded<A> {}
class NotSelfBounded<T> {
	T element;
	NotSelfBounded<T> set(T arg) {
		element = arg;
		return this;
	}
	T get() {
		return element;
	}
}

class A2 extends NotSelfBounded<A2> {}
class B2 extends NotSelfBounded<A2> {}
class C2 extends NotSelfBounded<C2> {
	C2 setAndGet(C2 arg) {
		return set(arg).get();		
	}
}
class D2 {}
class E2 extends NotSelfBounded<D2> {} //Без ограничения это теперь возможно
class F2 extends NotSelfBounded {}


public class NotSelfBounding {

	public static void main(String[] args) {
		A2 a = new A2();
		a.set(new A2());
		a = a.set(new A2()).get();
		a = a.get();
		C2 c = new C2();
		c.setAndGet(new C2());

	}

}
