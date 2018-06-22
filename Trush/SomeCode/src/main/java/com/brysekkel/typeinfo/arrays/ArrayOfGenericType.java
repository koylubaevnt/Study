package com.brysekkel.typeinfo.arrays;

public class ArrayOfGenericType<T> {

	T[] array;//OK
	
	public ArrayOfGenericType(int size) {
		//array = new T[size];//Ошибка
		array = (T[]) new Object[size];//предупреждение
	}

	//Ошибка
	//public <U> U[] makeArrayError() {
	//	return new U[10];
	//}
	
	public <U> U[] makeArray() {
		return (U[]) new Object[10];
	}
}
