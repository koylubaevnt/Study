package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsAndCovariance {

	public static void main(String[] args) {
		List<? extends Fruit> fruits = new ArrayList<Apple>();
		//ошибка компиляции добавление объектов невозможно!!!
		//fruits.add(new Apple());
		//fruits.add(new Fruit());
		//fruits.add(new Object());
		fruits.add(null);//Допустимо, но не интересно
		//Возвращается как минимум Fruit. РАЗРЕШЕНО
		Fruit f = fruits.get(0); 
		
	}
	
}
