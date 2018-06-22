package com.brysekkel.typeinfo.containers;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Maps {

	public static void printKeys(Map<Integer, String> map) {
		System.out.println("Size: " + map.size() + ", " +
						   "Keys: " + map.keySet());
	}
	
	public static void test(Map<Integer, String> map) {
		System.out.println(map.getClass().getSimpleName());
		map.putAll(new CountingMapData(25));
		//Поведение Set для ключей
		map.putAll(new CountingMapData(25));
		printKeys(map);
		System.out.println("Values: " + map.values());
		System.out.println(map);
		System.out.println("map.containsKey(11): " + map.containsKey(11));
		System.out.println("map.get(11): " + map.get(11));
		System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));
		Integer key = map.keySet().iterator().next();
		System.out.println("First key in map: " + key);
		map.remove(key);
		printKeys(map);
		map.clear();
		System.out.println("map.isEmpty(): " + map.isEmpty());
		map.putAll(new CountingMapData(25));
		map.keySet().removeAll(map.keySet());
		System.out.println("map.isEmpty(): " + map.isEmpty());
	}
	
	public static void main(String[] args) {
		test(new HashMap<>());
		test(new TreeMap<>());
		test(new LinkedHashMap<>());
		test(new IdentityHashMap<>());
		test(new ConcurrentHashMap<>());
		test(new WeakHashMap<>());		
	}

}
