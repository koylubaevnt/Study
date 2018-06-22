package com.koylubaevnt.stepik.java.basecourse.functional;

import static org.junit.Assert.assertEquals;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

public class TernaryOperatorTest {

	@Test
	public void test1() {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = TernaryOperator.ternaryOperator(condition, ifTrue, ifFalse);
        int v = safeStringLength.apply(null);
        assertEquals(0, v);
	}
	
	@Test
	public void test2() {
        Predicate<Object> condition = Objects::isNull;
        Function<Object, Integer> ifTrue = obj -> 0;
        Function<CharSequence, Integer> ifFalse = CharSequence::length;
        Function<String, Integer> safeStringLength = TernaryOperator.ternaryOperator(condition, ifTrue, ifFalse);
        int v = safeStringLength.apply("test");
        assertEquals(4, v);
	}
	
}
