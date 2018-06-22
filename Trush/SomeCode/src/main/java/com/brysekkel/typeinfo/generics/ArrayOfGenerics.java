package com.brysekkel.typeinfo.generics;

class Generic<T> {}

public class ArrayOfGenerics {

	static final int SIZE = 100;
	static Generic<Integer>[] gia;
	
	public static void main(String[] args) {
		//Компилируется выдает ClassCastException
		//gia = (Generic<Integer>[]) new Object[SIZE];
		
		gia = new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());

		gia[0] = new Generic<Integer>();
		//gia[1] = new Object(); //Ошибка времени компиляции
		//gia[2] = new Generic<Double>(); //Ошибка времени компиляции (несовместимый тип)
	}

}
