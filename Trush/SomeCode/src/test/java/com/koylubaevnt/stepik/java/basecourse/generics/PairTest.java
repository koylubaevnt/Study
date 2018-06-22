package com.koylubaevnt.stepik.java.basecourse.generics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PairTest {

	@Test
	public void test1() {
		Pair<Integer, String> pair = Pair.of(1, "hello");
		Integer i = pair.getFirst(); // 1
		String s = pair.getSecond(); // "hello"
		assertEquals(new Integer(1), i);
		assertEquals("hello", s);
	}

	@Test
	public void test2() {
		Pair<Integer, String> pair = Pair.of(1, "hello");
		Pair<Integer, String> pair2 = Pair.of(1, "hello");
		boolean mustBeTrue = pair.equals(pair2); // true!
		assertEquals(true, mustBeTrue);
	}
	
	@Test
	public void test3() {
		Pair<Integer, String> pair = Pair.of(1, "hello");
		Pair<Integer, String> pair2 = Pair.of(1, "hello");
		boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
		assertEquals(true, mustAlsoBeTrue);
	}
}
