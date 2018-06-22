package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;
import java.util.Collections;

public class AlphabeticSearch {

	public static void main(String[] args) {
		String[] sa = Generated.array(new String[20], new RandomGenerator.String(5));
		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(sa));
		Arrays.sort(sa);
		System.out.println("After sorting:");
		System.out.println(Arrays.toString(sa));
		Arrays.sort(sa, Collections.reverseOrder());
		System.out.println("After rerverse sorting:");
		System.out.println(Arrays.toString(sa));
		Arrays.sort(sa, String.CASE_INSENSITIVE_ORDER);
		System.out.println("After case-insensitive sorting:");
		System.out.println(Arrays.toString(sa));
		
	}

}
