package com.brysekkel.typeinfo.generics;

import java.util.Iterator;

public class FibonacciIterable extends Fibonacci implements Iterable<Integer> {

	private int n;
	
	public FibonacciIterable(int n) {
		this.n = n;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return n > 0;
			}

			@Override
			public Integer next() {
				n--;
				return FibonacciIterable.this.next();
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
				
	}

	public static void main(String[] args) {
		for(int i : new FibonacciIterable(18)) {
			System.out.print(i + " ");
		}

	}

}
