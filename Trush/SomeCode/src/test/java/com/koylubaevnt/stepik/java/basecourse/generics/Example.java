package com.koylubaevnt.stepik.java.basecourse.generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

public class Example<X> {
	/*
	@Test
    public void someMethod1(Object obj) {
		if (obj instanceof X) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}
    }
	*/
	
	public void testCollection() {
		Collection<?> collection = new ArrayList<>();
		Object object = new Object();
		 collection.contains(object);
		 collection.iterator();
		 collection.clear();
		 //collection.addAll(Arrays.asList(object));
		 collection.remove(object);
		 collection.toArray();
		 collection.size();
		 //collection.add(object); 
	}
	
	@Test
    public void someMethod2(Object obj) {
		X t = (X) obj;
    }
	/*
	@Test
    public void someMethod3(Object obj) {
		X t = new X();
    }
	
	@Test
    public void someMethod4(Object obj) {
		if (obj instanceof Optional<X>) {
			System.out.println("1");
		} else {
			System.out.println("0");
		}
    }
	*/
	@Test
    public void someMethod5(Object obj) {
		//X[] p = new X[10];
		X[] p = (X[])new Object[10];
    }
	
	@Test
    public void someMethod6(Object obj) {
		Optional<X> t = Optional.empty();
    }
	
}

/*
Какие операции разрешены внутри метода? ("Разрешены" = "Компилятор скомпилирует")
Проверка (obj instanceof X)
Приведение obj к типу X
Создание экземпляра X
Проверка (obj instanceof Optional<x>)
Создание массива X
Получение экземпляра Optional<x> через Optional.empty()
*/