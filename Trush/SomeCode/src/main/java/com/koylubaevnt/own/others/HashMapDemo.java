package com.koylubaevnt.own.others;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

	static class Test {
		int i;
		
		@Override
		public int hashCode() {
			return i;
		}
	}
	
	static class Test2 {
		int i;
		
		@Override
		public boolean equals(Object obj) {
			if(this == obj) return true;
			if(!(obj instanceof Test2)) return false;
			int objI = ((Test2) obj).i;
			return i == objI;
		}
	}
	
	public static void main(String[] args) {
		
		Test t1 = new Test();
		t1.i = 42;
		Test t2 = new Test();
		t2.i = 43;
		
		Map<Test, Integer> map = new HashMap<>();
		map.put(t1, 1);
		//map.put(t2, 2);
		
		System.out.println(t1.equals(t2));
		System.out.println(map.containsKey(t1));
		System.out.println(map.containsKey(t2));
		System.out.println(map.get(t1));
		System.out.println(map.get(t2));
		
		System.out.println("==================================");
		
		Test2 t21 = new Test2();
		t21.i = 42;
		Test2 t22 = new Test2();
		t22.i = 43;
		
		Map<Test2, Integer> map2 = new HashMap<>();
		map2.put(t21, 1);
		//map2.put(t22, 2);
		
		System.out.println(t21.equals(t22));
		System.out.println(t21.hashCode());
		System.out.println(t22.hashCode());
		System.out.println(map2.containsKey(t21));
		System.out.println(map2.containsKey(t22));
		System.out.println(map2.get(t21));
		System.out.println(map2.get(t22));
		
	}
	
}
