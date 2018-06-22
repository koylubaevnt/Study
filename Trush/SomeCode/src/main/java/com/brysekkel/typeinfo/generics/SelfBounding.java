package com.brysekkel.typeinfo.generics;

//Самоограничение требует использовать  класс в отношении наследования следующего вида: class A extends SelfBounded<A> {}
class SelfBounded<T extends SelfBounded<T>> {
	T element;
	SelfBounded<T> set(T arg) {
		element = arg;
		return this;
	}
	T get() {
		return element;
	}
}

class A extends SelfBounded<A> {}
class B extends SelfBounded<A> {}
class C extends SelfBounded<C> {
	C setAndGet(C arg) {
		return set(arg).get();		
	}
}
class D {}
//class E extends SelfBounded<D> {}// невозможно, тип D не соответствует ограничению 
class F extends SelfBounded {}//Возможно, есть предупреждение (RAW type). Принудить к выполнению идиомы не получится


public class SelfBounding {

	public static void main(String[] args) {
		A a = new A();
		a.set(new A());
		a = a.set(new A()).get();
		a = a.get();
		C c = new C();
		c.setAndGet(new C());

	}

}
