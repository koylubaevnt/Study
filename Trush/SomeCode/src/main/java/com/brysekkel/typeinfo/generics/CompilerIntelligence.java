package com.brysekkel.typeinfo.generics;

import java.util.Arrays;
import java.util.List;

public class CompilerIntelligence {

	public static void main(String[] args) {
		List<? extends Fruit> fruits =
				Arrays.asList(new Apple());
		
		Apple a = (Apple) fruits.get(0);
		fruits.contains(new Apple());
		fruits.indexOf(new Apple());

	}

}
