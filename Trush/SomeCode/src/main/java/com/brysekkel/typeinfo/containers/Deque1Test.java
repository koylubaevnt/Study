package com.brysekkel.typeinfo.containers;

public class Deque1Test {

	static void fillTest(Deque1<Integer> deque) {
		for(int i = 20; i < 27; i++) {
			deque.addFirst(i);
		}
		for(int i = 50; i < 55; i++) {
			deque.addLast(i);
		}
	}
	
	public static void main(String[] args) {
		Deque1<Integer> di = new Deque1<>();
		fillTest(di);
		System.out.println(di);
		while(di.size() != 0) {
			System.out.print(di.removeFirst() + " ");
		}
		System.out.println();
		fillTest(di);
		while(di.size() != 0) {
			System.out.print(di.removeLast() + " ");
		}

	}

}
