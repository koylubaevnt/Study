package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;
import java.util.Collections;

public class Reverse {

	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a, Collections.reverseOrder());
		System.out.println("After sorting:");
		System.out.println(Arrays.toString(a));
	}

}
