package com.brysekkel.typeinfo.generics;

import java.util.HashSet;
import java.util.Set;

public class Sets {

	//Объединение множеств
	public static <T> Set<T> union(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<>(a);
		result.addAll(b);
		return result;
	}
	
	//пересечение множеств
	public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<>(a);
		result.retainAll(b);
		return result;
	}
	
	//вычитание множеств
	public static <T> Set<T> difference(Set<T> a, Set<T> b) {
		Set<T> result = new HashSet<>(a);
		result.removeAll(b);
		return result;
	}
	
	//Рефлексивность (все что не входит в первое множество)
	public static <T> Set<T> complement(Set<T> a, Set<T> b) {
		return difference(union(a, b), intersection(a, b));
	}
}
