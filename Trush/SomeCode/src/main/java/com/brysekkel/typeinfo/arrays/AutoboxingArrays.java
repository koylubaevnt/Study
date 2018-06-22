package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;

public class AutoboxingArrays {

	public static void main(String[] args) {
		Integer[][] a = {
				{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
				{ 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
				{ 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 },
				{ 71, 72, 73, 74, 75, 76, 77, 78, 79, 80 },
		};
		System.out.println(Arrays.deepToString(a));
	}

}
