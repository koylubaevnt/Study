package com.brysekkel.typeinfo.generics;

import java.lang.reflect.Array;
import java.util.Arrays;

public class GenericArrayWithTypeToken<T> {

	private T[] array;
	
	public GenericArrayWithTypeToken(Class<T> type, int sz) {
		array = (T[]) Array.newInstance(type, sz);
	}
	
	public void put(int index, T item) {
		array[index] = item;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public T[] rep() {
		return array;
	}
	
	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gai =
				new GenericArrayWithTypeToken<>(Integer.class, 10);
		for(int i = 0; i < 10; i++) {
			gai.put(i, i);
		}
		for(int i = 0; i < 10; i++) {
			System.out.print(gai.get(i) + " ");
		}
		System.out.println();
		try {
			Integer[] ia = gai.rep();
			System.out.println(Arrays.toString(ia));
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
