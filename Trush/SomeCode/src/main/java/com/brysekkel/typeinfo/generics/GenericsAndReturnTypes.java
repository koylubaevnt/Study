package com.brysekkel.typeinfo.generics;

interface GenericGetter<T extends GenericGetter<T>> {
	T get();
}

interface Getter extends GenericGetter<Getter> {}

public class GenericsAndReturnTypes {

	void test(Getter g) {
		Getter result = g.get();
		GenericGetter gg = g.get();//Так же базовый класс
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
