package com.brysekkel.typeinfo.generics;

class Subtype extends BasicHolder<Subtype> {}

public class CRGWithBasicHolder {

	public static void main(String[] args) {
		Subtype st1 = new Subtype();
		Subtype st2 = new Subtype();
		st1.set(st2); // Используем функции из BasicHolder. Но относятся к точному типу Subtype
		Subtype st3 = st1.get(); // Используем функции из BasicHolder. Но относятся к точному типу Subtype
		st1.f(); // Используем функции из BasicHolder. Но относятся к точному типу Subtype
	}
	
}
