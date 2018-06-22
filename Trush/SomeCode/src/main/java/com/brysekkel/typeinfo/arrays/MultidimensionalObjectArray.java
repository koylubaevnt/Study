package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;

public class MultidimensionalObjectArray {

	public static void main(String[] args) {
		BerylliumSphere[][] a = {
				{ new BerylliumSphere(), new BerylliumSphere() },
				{ new BerylliumSphere(), new BerylliumSphere(),
				  new BerylliumSphere(), new BerylliumSphere()},
				{ new BerylliumSphere(), new BerylliumSphere(),
				  new BerylliumSphere(), new BerylliumSphere(),
				  new BerylliumSphere(), new BerylliumSphere(),
				  new BerylliumSphere(), new BerylliumSphere()}
		};
		System.out.println(Arrays.deepToString(a));
	}

}
