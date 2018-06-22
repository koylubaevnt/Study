package com.brysekkel.typeinfo.generics;

public class Erased<T> {

	private final int SIZE = 100;
	public  void f(Object arg) {
		//if(arg instanceof T) {}					//Ошибка
		//T var = new T();						//Ошибка
		//T[] array = new T[SIZE];				//Ошибка
		T[] array2 = (T[])new Object[SIZE];		//Предупреждение
	}
	
}