package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.util.Comparator;
import java.util.Queue;
import java.util.Scanner;

/**
 * Задача на программирование: очередь с приоритетами
 * 
 * Первая строка входа содержит число операций 1 ≤ n ≤ 10^5. Каждая из последующих n
 * строк задают операцию одного из следующих двух типов:
 *     Insert x, где 0 ≤ x ≤ 10^9 — целое число;
 *     ExtractMax.
 * Первая операция добавляет число x в очередь с приоритетами, вторая — извлекает максимальное число и выводит его.
 * 
 * Sample Input:
 	6
	Insert 200
	Insert 10
	ExtractMax
	Insert 5
	Insert 500
	ExtractMax

  *	Sample Output:
  *	200
  * 500
  * 
 * @author koylu
 *
 */
public class PriorityQueue {

	private static final String INSERT = "Insert";
	private static final String EXTRACT = "ExtractMax";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int countOperations = scanner.nextInt();
		Queue<Integer> priorityQueue = new java.util.PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -Integer.compare(o1, o2);
			}
		});
		String command;
		Integer number;
		Integer extractedNumber;
		while(countOperations > 0) {
			command = scanner.next();
			if(command.equals(INSERT)) {
				number = scanner.nextInt();
				priorityQueue.add(number);
			} else if(command.equals(EXTRACT)) {
				extractedNumber = priorityQueue.remove();
				System.out.println(extractedNumber);
			}
			countOperations--;
		}
		scanner.close();
	}

}
