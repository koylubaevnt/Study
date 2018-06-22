package com.brysekkel.typeinfo.generics;

import static com.brysekkel.typeinfo.generics.Sets.*;
import static com.brysekkel.typeinfo.generics.Watercolors.*;

import java.util.EnumSet;
import java.util.Set;

public class WatercolorSets {

	public static void main(String[] args) {
		Set<Watercolors> set1 = 
				EnumSet.range(BRILIANT_RED, VIRIDIAN_HUE);
		Set<Watercolors> set2 = 
				EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
		System.out.println("set1: " + set1 + ", count: " + set1.size());
		System.out.println("set2: " + set2 + ", count: " + set2.size());
		System.out.println("union(set1, set2): " + union(set1, set2) + ", count: " + union(set1, set2).size());
		Set<Watercolors> subset = 
			intersection(set1, set2);
		System.out.println("intersection(set1, set2): " + subset + ", count: " + subset.size());
		System.out.println("difference(set1, subset): " + difference(set1, subset) + ", count: " + difference(set1, subset).size());
		System.out.println("difference(set2, subset): " + difference(set2, subset) + ", count: " + difference(set2, subset).size());
		System.out.println("complement(set1, set2): " + complement(set1, set2) + ", count: " + complement(set1, set2).size());
	}
	
}
