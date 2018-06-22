package com.brysekkel.typeinfo.arrays;

import com.brysekkel.typeinfo.generics.Generator;

public class Generated {

	public static <T> T[] array(T[] a, Generator<T> gen) {
		return new CollectionData<T>(gen, a.length).toArray(a);
	}
	
	public static <T> T[] array(Class<T> type, Generator<T> gen, int size) {
		T[] a = (T[]) java.lang.reflect.Array.newInstance(type, size);
		return new CollectionData<T>(gen, size).toArray(a);
	}

}
