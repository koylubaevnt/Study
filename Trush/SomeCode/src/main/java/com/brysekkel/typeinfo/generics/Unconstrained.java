package com.brysekkel.typeinfo.generics;

class Other {}
class BasicOther extends BasicHolder<Other> {}

public class Unconstrained {

	public static void main(String[] args) {
		BasicOther b = new BasicOther();
		b.set(new Other()); // Используем функции из BasicHolder. Но относятся к точному типу Other
		Other other = b.get(); // Используем функции из BasicHolder. Но относятся к точному типу Other
		b.f(); // Используем функции из BasicHolder. Но относятся к точному типу Other

	}

}
