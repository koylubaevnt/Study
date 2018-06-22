package com.brysekkel.typeinfo.containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortedMapDemo {

	public static void main(String[] args) {
		SortedMap<Integer, String> sortedMap = new TreeMap<>(new CountingMapData(10));
		System.out.println(sortedMap);
		Integer low = sortedMap.firstKey();
		Integer high = sortedMap.lastKey();
		System.out.println(low);
		System.out.println(high);
		Iterator<Integer> it = sortedMap.keySet().iterator();
		for(int i = 0; i <= 6; i++) {
			if (i == 3) {
				low = it.next();
			}
			if (i == 6) {
				high = it.next();
			} else {
				it.next();
			}
		}
		System.out.println(low);
		System.out.println(high);
		System.out.println(sortedMap.subMap(low, high));
		System.out.println(sortedMap.headMap(high));
		System.out.println(sortedMap.tailMap(low));
	}

}

