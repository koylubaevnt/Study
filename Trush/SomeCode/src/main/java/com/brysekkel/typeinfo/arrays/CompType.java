package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;
import java.util.Random;

import com.brysekkel.typeinfo.generics.Generator;

public class CompType implements Comparable<CompType>{

	int i;
	int j;
	private static int count = 1;
	
	public CompType(int i, int j) {
		this.i = i;
		this.j = j;
	}

	
	
	@Override
	public String toString() {
		String result = "CompType [i=" + i + ", j=" + j + "]";
		if(count++ % 3 == 0) {
			result += "\n";
		}
		return result;
	}

	@Override
	public int compareTo(CompType o) {
		return ( i < o.i ? -1 : (i == o.i ? 0 : 1));
	}
	
	private static Random r = new Random(47);
	public static Generator<CompType> generator() {
		return new Generator<CompType>() {

			@Override
			public CompType next() {
				return new CompType(r.nextInt(100), r.nextInt(100));
			}
		};
	}
	
	public static void main(String[] args) {
		CompType[] a = Generated.array(new CompType[12], generator());
		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(a));
		Arrays.sort(a);
		System.out.println("After sorting:");
		System.out.println(Arrays.toString(a));
		
	}
}
