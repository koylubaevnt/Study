package com.brysekkel.typeinfo.generics;

class Manipulator<T extends HasF> {
	private T obj;
	public Manipulator(T x) {
		obj = x;
	}
	public void manipulate() {
		obj.f();//Не найти метод если класс описан как class Manipulator<T> 
	}
}

public class HasFManipulator {

	public static void main(String[] args) {
		HasF hf = new HasF();
		Manipulator<HasF> manipulator =
				new Manipulator<>(hf);
		manipulator.manipulate();

	}

}
