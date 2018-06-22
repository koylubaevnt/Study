package com.brysekkel.typeinfo.containers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class SetType {
	int i;
	public SetType(int i) {
		this.i = i;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof SetType && (i == ((SetType)obj).i);
	}
	@Override
	public String toString() {
		return Integer.toString(i);
	}
}

class HashType extends SetType {
	public HashType(int i) {
		super(i);
	}
	@Override
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {
	public TreeType(int i) {
		super(i);
	}
	@Override
	public int compareTo(TreeType o) {
		return (o.i < i ? -1 : (o.i == i ? 0 : 1));
	}
}

public class TypesForSets {
	
	static <T> Set<T> fille(Set<T> set, Class<T> type) {
		try {
			for(int i = 0; i < 10; i++) {
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	
	static <T> void test(Set<T> set, Class<T> type) {
		fille(set, type);
		fille(set, type);
		fille(set, type);
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		test(new HashSet<>(), HashType.class);
		test(new LinkedHashSet<>(), HashType.class);
		test(new TreeSet<>(), TreeType.class);
		
		//Работают неправильно, т.к. не определен метод hashCode
		test(new HashSet<>(), SetType.class);
		test(new HashSet<>(), TreeType.class);
		test(new LinkedHashSet<>(), SetType.class);
		test(new LinkedHashSet<>(), TreeType.class);
		//А тут не реализуют интерфейс Comparable 
		try {
			test(new TreeSet<>(), SetType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<>(), HashType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

	}

}
