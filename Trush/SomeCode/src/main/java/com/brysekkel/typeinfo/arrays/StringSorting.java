package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;
import java.util.Collections;

import com.brysekkel.typeinfo.generics.Generator;

public class StringSorting {

	public static void main(String[] args) {
		String[] sa = Generated.array(new String[30], new RandomGenerator.String(5));
		Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
		System.out.println("Sorted array: " + Arrays.toString(sa));
		//int index = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
		int index = Arrays.binarySearch(sa, sa[10], String.CASE_INSENSITIVE_ORDER);
		System.out.println("Index: " + index + "\n" + sa[index]);
	}

}
