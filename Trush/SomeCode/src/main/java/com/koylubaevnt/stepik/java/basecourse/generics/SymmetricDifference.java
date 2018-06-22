package com.koylubaevnt.stepik.java.basecourse.generics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SymmetricDifference {

	/*
	 * Реализуйте метод, вычисляющий симметрическую разность двух множеств.
	 * Метод должен возвращать результат в виде нового множества. Изменять переданные в него множества не допускается.
	 * 
	 * Симметрическая разность двух множеств — теоретико-множественная операция, результатом которой является новое 
	 * множество, включающее все элементы исходных множеств, не принадлежащие одновременно обоим исходным множествам.
	 * 
	 * Пример
	 * Симметрическая разность множеств {1, 2, 3} и {0, 1, 2} равна {0, 3}.
	 * 
	 */
	
	public static void main(String[] args) {
		reverseOddOrderDigit();
		/*
		Set<Integer> set1 = new HashSet<>();
		set1.add(1);
		set1.add(2);
		set1.add(3);
		Set<Integer> set2 = new HashSet<>();
		set2.add(0);
		set2.add(1);
		set2.add(2);
		Set<Integer> s = symmetricDifference(set1, set2);
		s.forEach(System.out::println);
		*/
	}
	
	public static <T> Set<T> symmetricDifference(Set<? extends T> set1, Set<? extends T> set2) {
	    Set<T> unionSet = new HashSet<>();
	    for (Object object : set1) {
			if(!set2.contains(object)) {
				unionSet.add((T)object);
			}
		}
	    for (Object object : set2) {
			if(!set1.contains(object)) {
				unionSet.add((T)object);
			}
		}
		return unionSet;
	}
	
	/*
	 * Напишите программу, которая прочитает из System.in последовательность целых чисел, разделенных пробелами, 
	 * затем удалит из них все числа, стоящие на четных позициях, и затем выведет получившуюся последовательность 
	 * в обратном порядке в System.out.
	 * 
	 * Все числа влезают в int. Позиции чисел в последовательности нумеруются с нуля.
	 * В этом задании надо написать программу целиком, включая import'ы, декларацию класса Main и метода main.
	 * 
	 * Sample Input:
	 * 1 2 3 4 5 6 7
	 * 
	 * Sample Output:
	 * 6 4 2
	 */
	public static void reverseOddOrderDigit() {
		Scanner scanner = new Scanner(System.in);
		Deque<Integer> stack = new ArrayDeque<>();
		int order = 0;
		int readInt;
		while(scanner.hasNext()) {
			readInt = scanner.nextInt();
			if (order % 2 != 0) {
				stack.push(readInt);
			}
			order++;
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}
}
