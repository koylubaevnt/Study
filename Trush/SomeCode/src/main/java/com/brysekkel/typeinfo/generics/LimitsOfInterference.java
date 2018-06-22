package com.brysekkel.typeinfo.generics;

import java.util.List;
import java.util.Map;

public class LimitsOfInterference {

	static void f(Map<Integer, List<? extends String>> par) { System.out.println(par.getClass().getName());}
	
	public static void main(String[] args) {
		f(New.map()); //не компилируется в Java 5 (в Java 8 проблем нет)
	}

}
