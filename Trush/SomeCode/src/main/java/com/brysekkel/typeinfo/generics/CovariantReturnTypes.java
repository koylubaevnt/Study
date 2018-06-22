package com.brysekkel.typeinfo.generics;

class Base {
	void base() {};	
}
class Derived extends Base {}
interface OrdinaryGetter {
	Base get();
}
interface DerivedGetter extends OrdinaryGetter {
	//Возвращаемый тип переопределенного метода может изменяться
	@Override
	Derived get();
}


public class CovariantReturnTypes {

	void test(DerivedGetter d) {
		Derived d2 = d.get();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
