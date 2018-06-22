package com.koylubaevnt.stepik.java.basecourse.streams;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.function.BiConsumer;

import org.junit.Test;

public class MinMaxInStreamTest {

	@Test
	public void test1() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(-100500);
		list.add(30);
		list.add(100500);

		BiConsumer<Integer, Integer> tester1 = (min, max) -> {
			assertEquals(min.intValue(), -100500);
			assertEquals(max.intValue(), 100500);
		};
		MinMaxInStream.findMinMax(list.stream(), Integer::compare, tester1);
	}

	@Test
	public void test2() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(-100500);
		list.add(30);
		list.add(100500);
		BiConsumer<Integer, Integer> tester2 = (min, max) -> {
			assertEquals(100500, min.intValue());
			assertEquals(-100500, max.intValue());
		};
		MinMaxInStream.findMinMax(list.stream(), (x, y) -> Integer.compare(y, x), tester2);
	}

	@Test
	public void test3() {
		ArrayList<Integer> list = new ArrayList<>();
		BiConsumer<Integer, Integer> tester3 = (min, max) -> {
			assertEquals(null, min);
			assertEquals(null, max);
		};
		MinMaxInStream.findMinMax(list.stream(), Integer::compare, tester3);
	}

	@Test
	public void test4() {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(9000);
		BiConsumer<Integer, Integer> tester4 = (min, max) -> {
			assertEquals(9000, min.intValue());
			assertEquals(9000, max.intValue());
		};
		MinMaxInStream.findMinMax(list.stream(), Integer::compare, tester4);
	}
}
