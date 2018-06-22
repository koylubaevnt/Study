package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

class CompTypeComparator implements Comparator<CompType> {

	@Override
	public int compare(CompType o1, CompType o2) {
		return ( o1.j < o2.j ? -1 : (o1.i == o2.i ? 0 : 1));
	}
	
}

public class ComparatorTest {

	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], CompType.generator());
		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a, new CompTypeComparator());
		System.out.println("After sorting:");
		System.out.println(Arrays.toString(a));
	}

}
