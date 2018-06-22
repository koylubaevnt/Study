package com.brysekkel.typeinfo.containers;

import java.util.LinkedHashSet;
import java.util.Set;

import com.brysekkel.typeinfo.arrays.CollectionData;
import com.brysekkel.typeinfo.generics.Generator;

class Goverment implements Generator<String> {
	String[] foundation = ("strange woman luing in pounds " +
		"distributing sqords is no basis for a system of " +
		"goverment").split(" ");

	private int index;
	
	@Override
	public String next() {
		return foundation[index++];
	}

}

public class CollectionDataTest {

	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<>(
				new CollectionData<>(new Goverment(), 15));
		System.out.println(set);
		Set<String> set1 =  new LinkedHashSet<>();
		set1.addAll(CollectionData.list(new Goverment(), 15));
		System.out.println(set1);
		

	}
}
