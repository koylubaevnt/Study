package com.brysekkel.typeinfo.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Synchronization {

	
	public static void main(String[] args) {
		Collection<String> c = 
				Collections.synchronizedCollection(new ArrayList<>());
		
		List<String> a = Collections.synchronizedList(new ArrayList<>());

		Set<String> s = Collections.synchronizedSet(new HashSet<>());
		
		Set<String> ss = Collections.synchronizedSortedSet(new TreeSet<>());
		
		Map<String, String> m = Collections.synchronizedMap(new HashMap<>());
		
		Map<String, String> mm = Collections.synchronizedSortedMap(new TreeMap<>());
		
	}

}
