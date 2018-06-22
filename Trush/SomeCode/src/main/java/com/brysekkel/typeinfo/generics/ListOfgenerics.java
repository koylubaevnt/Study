package com.brysekkel.typeinfo.generics;

import java.util.ArrayList;
import java.util.List;

public class ListOfgenerics<T> {
	private List<T> array = new ArrayList<>();
	public void add(T item) {
		array.add(item);
	}
	public T get(int index) {
		return array.get(index);
	}
}
