package com.koylubaevnt.stepik.csc.algorithms.common;

import java.util.Date;
import java.util.Scanner;

/**
 * 
 * Скорость роста: от меньшего к большему log n < sqrt(n) < n < n log n < n^2 <
 * 2^n
 * 
 * @author koylu
 *
 */

public class GCD {

	/**
	 * Реализация поиска НОД для двух чисел на основе простого варианта
	 * 	Если a = 0, то НОД - число b
	 * 	Иначе Если b = 0, то НОД - число a
	 *  Иначе Если a = b, то НОД - число a или и (без разницы)
	 *  Иначе НОД - это наибольшее число, которое делится на a и b, срелди чисел от 2 до min(a, b)
	 *  
	 * @param a	Первое число
	 * @param b Второе число
	 * @return	НОД
	 */
	public static long naiveGCD(long a, long b) {
		if (a == 0) {
			return b;
		} else if (b == 0 || a == b) {
			return a;
		}
		long gcd = 1;
		long min = Math.min(a, b);
		for (long i = 2; i <= min; i++) {
			if ((a % i == 0) && (b % i == 0)) {
				gcd = i;
			}
		}
		return gcd;
	}

	/**
	 * Реализация поиска НОД для двух чисел на основе простого варианта
	 * 
	 * Основывается на основе следующего:
	 * 	a = d1 * d2, где выполняется
	 *  dmin = min(d1, d2) <= sqrt(a)
	 *  dmax = max(d1, d2) >= sqrt(a)
	 *  dmax = a / dmin;
	 *  
	 *  следовательно, если a делится без остатка на dmin и b делится без остатка на dmin, то это их общий делитель. 
	 *  Далее находим dmax и если b делится без остатка на dmax, то это НОД    
	 * 
	 *  
	 * @param a	Первое число
	 * @param b Второе число
	 * @return	НОД
	 */
	
	public static long naive2GCD(long a, long b) {
		long gcd = 1;
		for (long i = 1; i * i <= a; i++) {
			if (a % i == 0) {
				if (b % i == 0) {
					gcd = i;
				}
				long big_i = a / i;
				if (b % big_i == 0) {
					return big_i;
				}
			}
		}
		return gcd;
	}

	/**
	 * Реализация поиска НОД для двух чисел на основе алгоритма Евклида (рекурсивный вариант)
	 *  
	 * @param a	Первое число
	 * @param b Второе число
	 * @return	НОД
	 */
	public static long euclidRecursionGCD(long a, long b) {
		if (b > 0) {
			return euclidRecursionGCD(b, a % b);
		} else {
			return a;
		}
	}

	/**
	 * Реализация поиска НОД для двух чисел на основе алгоритма Евклида
	 *  
	 * @param a	Первое число
	 * @param b Второе число
	 * @return	НОД
	 */
	public static long euclidGCD(long a, long b) {
		long t;
		while (true) {
			if (b > 0) {
				t = a % b;
				a = b;
				b = t;
			} else {
				return a;
			}
		}
	}
	
	private static final int COUNT_ALGORITHMS = 4;

	/**
	 * Функция для тестирования алгоритма НОД\GCD (наибольший общий делитель\Greatest Common Divisor)
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		long a = scanner.nextLong();
		long b = scanner.nextLong();
		long[] result = new long[COUNT_ALGORITHMS];
		String[] name = new String[COUNT_ALGORITHMS];
		long[] timeElapsed = new long[COUNT_ALGORITHMS];

		long startTime = new Date().getTime();
		name[0] = "Наивный";
		result[0] = naiveGCD(a, b);
		timeElapsed[0] = new Date().getTime() - startTime;

		startTime = new Date().getTime();
		name[1] = "Наивный 2";
		result[1] = naive2GCD(a, b);
		timeElapsed[1] = new Date().getTime() - startTime;

		startTime = new Date().getTime();
		name[2] = "Евклида (рекурсивный)";
		result[2] = euclidRecursionGCD(a, b);
		timeElapsed[2] = new Date().getTime() - startTime;

		startTime = new Date().getTime();
		name[3] = "Евклида";
		result[3] = euclidGCD(a, b);
		timeElapsed[3] = new Date().getTime() - startTime;

		for (int i = 0; i < COUNT_ALGORITHMS; i++) {
			System.out.println(
					i + ". Algorithm: " + name[i] + ". Result: " + result[i] + ". Time elapsed: " + timeElapsed[i]);
		}

		scanner.close();
	}


}
