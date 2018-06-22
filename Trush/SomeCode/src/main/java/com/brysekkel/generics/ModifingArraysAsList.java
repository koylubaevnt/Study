package com.brysekkel.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ModifingArraysAsList {

	public static void main(String[] args) {
		Random random = new Random(47);
		Integer[] ia = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		List<Integer> list1 = new ArrayList<>(Arrays.asList(ia));
		System.out.println("До перемешивания: " + list1);
		Collections.shuffle(list1, random);
		System.out.println("После перемешивания: " + list1);
		System.out.println("Массив: " + Arrays.toString(ia));
		
		List<Integer> list2 = Arrays.asList(ia);
		System.out.println("До перемешивания: " + list2);
		Collections.shuffle(list2, random);
		System.out.println("После перемешивания: " + list2);
		System.out.println("Массив: " + Arrays.toString(ia));

	}

}
