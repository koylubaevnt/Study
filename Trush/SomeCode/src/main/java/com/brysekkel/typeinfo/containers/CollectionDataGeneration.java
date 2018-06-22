package com.brysekkel.typeinfo.containers;

import java.util.ArrayList;
import java.util.HashSet;

import com.brysekkel.typeinfo.arrays.CollectionData;
import com.brysekkel.typeinfo.arrays.RandomGenerator;

public class CollectionDataGeneration {

	public static void main(String[] args) {
		System.out.println(new ArrayList<>(
				CollectionData.list(
						new RandomGenerator.String(9), 10)));
		System.out.println(new HashSet<Integer>(
				new CollectionData<Integer>(
						new RandomGenerator.Integer(), 10)));

	}

}
