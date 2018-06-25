package com.brysekkel.typeinfo.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

public class MapPerfomance {

	static List<Test<Map<Integer, Integer>>> tests = 
			new ArrayList<>();
	
	static {
		tests.add(new Test<Map<Integer,Integer>>("put") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					for(int j = 0; j < size; j++) {
						container.put(j, j);
					}
				}
				return loops * size;
			}
		});
		
		tests.add(new Test<Map<Integer,Integer>>("get") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops;
				int size = tp.size * 2;
				for(int i = 0; i < loops; i++) {
					for(int j = 0; j < size; j++) {
						container.get(j);
					}
				}
				return loops * size;
			}
		});
		
		tests.add(new Test<Map<Integer,Integer>>("iterate") {
			
			@Override
			int test(Map<Integer, Integer> container, TestParam tp) {
				int loops = tp.loops * 10;
				for(int i = 0; i < loops; i++) {
					Iterator it = container.entrySet().iterator();
					while(it.hasNext()) {
						it.next();
					}
				}
				return loops * container.size();
			}
		});
	}
	
	public static void main(String[] args) {
		if(args.length > 0) {
			Tester.defaultParams = TestParam.array(args);
		}
		Tester.run(new TreeMap<>(), tests);
		Tester.run(new HashMap<>(), tests);
		Tester.run(new LinkedHashMap<>(), tests);
		Tester.run(new IdentityHashMap<>(), tests);
		Tester.run(new WeakHashMap<>(), tests);
		Tester.run(new Hashtable<>(), tests);

	}

}