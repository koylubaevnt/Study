package com.brysekkel.typeinfo.containers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Vector;

import com.brysekkel.typeinfo.arrays.CountingGenerator;
import com.brysekkel.typeinfo.arrays.Generated;

public class ListPerfomance {

	static Random random = new Random();
	static int reps = 1000;
	static List<Test<List<Integer>>> tests = 
			new ArrayList<>();
	static List<Test<LinkedList<Integer>>> qTests = 
			new ArrayList<>();
	
	static {
		tests.add(new Test<List<Integer>>("add") {
			
			@Override
			int test(List<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					for(int j = 0; j < listSize; j++) {
						container.add(j);
					}
				}
				return loops * listSize;
			}
		});
		
		tests.add(new Test<List<Integer>>("get") {
			
			@Override
			int test(List<Integer> container, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.get(random.nextInt(listSize));
				}
				return loops;
			}
		});
		
		tests.add(new Test<List<Integer>>("set") {
			
			@Override
			int test(List<Integer> container, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.set(random.nextInt(listSize), 47);
				}
				return loops;
			}
		});

		tests.add(new Test<List<Integer>>("iteradd") {
		
			@Override
			int test(List<Integer> container, TestParam tp) {
				final int LOOPS = 1_000_000;
				int half = container.size() / 2;
				ListIterator<Integer> it = container.listIterator(half);
				for(int i = 0; i < LOOPS; i++) {
					it.add(47);
				}
				return LOOPS;
			}
		
		});
		
		tests.add(new Test<List<Integer>>("insert") {
			
			@Override
			int test(List<Integer> container, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i < loops; i++) {
					container.add(5, 47);
				}
				return loops;
			}
		});
		
		tests.add(new Test<List<Integer>>("remove") {
			
			@Override
			int test(List<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					container.addAll(new CountingIntegerList(listSize));
					while(container.size() > 5) {
						container.remove(5);
					}
				}
				return loops * listSize;
			}
		});
		
		//Queue
		qTests.add(new Test<LinkedList<Integer>>("addFirst") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					for(int j = 0; j < listSize; j++) {
						container.addFirst(j);
					}
				}
				return loops * listSize;
			}
		});

		qTests.add(new Test<LinkedList<Integer>>("addLast") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					for(int j = 0; j < listSize; j++) {
						container.addLast(j);
					}
				}
				return loops * listSize;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("removeFirst") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					container.addAll(new CountingIntegerList(listSize));
					while(container.size() > 5) {
						container.removeFirst();
					}
				}
				return loops * listSize;
			}
		});

		qTests.add(new Test<LinkedList<Integer>>("removeLast") {
		
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.clear();
					container.addAll(new CountingIntegerList(listSize));
					while(container.size() > 5) {
						container.removeLast();
					}
				}
				return loops * listSize;
			}
		});
		/*
		qTests.add(new Test<LinkedList<Integer>>("get") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.get(random.nextInt(listSize));
				}
				return loops;
			}
		});
		
		qTests.add(new Test<LinkedList<Integer>>("set") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops * reps;
				int listSize = tp.size;
				for(int i = 0; i < loops; i++) {
					container.set(random.nextInt(listSize), 47);
				}
				return loops;
			}
		});

		qTests.add(new Test<LinkedList<Integer>>("iteradd") {
		
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				final int LOOPS = 1_000_000;
				int half = container.size() / 2;
				ListIterator<Integer> it = container.listIterator(half);
				for(int i = 0; i < LOOPS; i++) {
					it.add(47);
				}
				return LOOPS;
			}
		
		});
		
		qTests.add(new Test<LinkedList<Integer>>("insert") {
			
			@Override
			int test(LinkedList<Integer> container, TestParam tp) {
				int loops = tp.loops;
				for(int i = 0; i < loops; i++) {
					container.add(5, 47);
				}
				return loops;
			}
		});
		
		*/
		
	}
	
	static class ListTester extends Tester<List<Integer>> {

		public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
			super(container, tests);
		}
		
		@Override
		protected List<Integer> initialize(int size) {
			container.clear();
			container.addAll(new CountingIntegerList(size));
			return container;
		}
	
		public static void run(List<Integer> container,
				List<Test<List<Integer>>> tests) {
			new ListTester(container, tests).timedTest();
		}
		
	}
	
	public static void main(String[] args) {
		if(args.length > 0) {
			Tester.defaultParams = TestParam.array(args);
		}
		Tester<List<Integer>> arrayTest =
				new Tester<List<Integer>>(null, tests.subList(1, 3)) {
			
			@Override
			protected List<Integer> initialize(int size) {
				Integer[] ia = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
				return Arrays.asList(ia);
			}
		};
		arrayTest.setHeadline("Array as List");
		arrayTest.timedTest();
		Tester.defaultParams = TestParam.array(
				10, 5000, 100, 5000, 1000, 1000, 10000, 200);
		if(args.length > 0) {
			Tester.defaultParams = TestParam.array(args);
		}
		
		ListTester.run(new ArrayList<>(), tests);
		ListTester.run(new LinkedList<>(), tests);
		ListTester.run(new Vector<>(), tests);
		Tester.fieldWidth = 12;
		Tester<LinkedList<Integer>> qTest = 
				new Tester<LinkedList<Integer>>(new LinkedList<>(), qTests);
		qTest.setHeadline("Queue tests");
		qTest.timedTest();
	}

}
