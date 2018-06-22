package com.koylubaevnt.stepik.java.basecourse.streams;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class StreamsDemo {

	public static void main(String[] args) {
		sum();
		System.out.println(factorial(10));
		System.out.println(isPalindrome("asdfgf45"));
		System.out.println(isPalindrome("asd456654dsa"));
		
		IntStream s = IntStream.iterate(1, n -> n + 1)
				.filter(n -> n % 5 == 0 && n % 2 != 0)
				.limit(10)
				.map(n -> n * n);
		
	}

	public static void sum() {
		int sum = IntStream.iterate(1, n -> n + 1)
				.filter(n -> n % 5 == 0 && n % 2 != 0)
				.limit(10)
				.map(n -> n * n)
				.sum();
		System.out.println(sum);
	}
	
	public static BigInteger factorial(int n) {
		return IntStream.rangeClosed(1, n)
				.mapToObj(i -> BigInteger.valueOf(i))
				.reduce(BigInteger.ONE, BigInteger::multiply);
		
	}
	
	public static boolean isPalindrome(String s) {
		StringBuilder leftToRight = new StringBuilder();
		
		s.chars().filter(Character::isLetterOrDigit)
		.map(Character::toLowerCase)
		.forEach(leftToRight::appendCodePoint);
		
		StringBuilder rightToLeft = new StringBuilder(leftToRight).reverse();
		
		return leftToRight.toString().equals(rightToLeft.toString());
		
	}
}
