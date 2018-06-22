package com.brysekkel.typeinfo.enumerated;

import com.brysekkel.utils.notmine.Enums;

public class RoShamBo {

	public static <T extends Competitor<T>> void match(T a, T b) {
		System.out.println(a + " vs. " + b + ": " + a.complete(b));
	}
	
	public static <T extends Enum<T> & Competitor<T>> void play(Class<T> rsbClass, int size) {
		for(int i = 0; i < size; i++)
			match(Enums.random(rsbClass), Enums.random(rsbClass));
	}
}
