package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.util.Scanner;

/**
 * Задача на программирование: двоичный поиск 
 * 
 * В первой строке даны целое число 1 ≤ n ≤ 10^5 и массив A[1…n] из n различных натуральных чисел, 
 * не превышающих 10^9, в порядке возрастания, во второй — целое число 1 ≤ k ≤ 10^5 и k натуральных чисел b1,…,bk, 
 * не превышающих 10^9. Для каждого i от 1 до k необходимо вывести индекс 1 ≤ j ≤n, для которого A[j]=bi, или −1, 
 * если такого j нет.
 * 
 * Sample Input:
	5 1 5 8 12 13
	5 8 1 23 1 11
 *
 * Sample Output:
 * 	3 1 -1 1 -1
 * @author koylu
 *
 */
public class BinarySearch {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int k = scanner.nextInt();
		int[] b = new int[k];
		for(int i = 0; i < k; i++) {
			b[i] = scanner.nextInt();
		}
		for(int i = 0; i < b.length; i++) {
			System.out.print(linearSearchSortedArray(a, b[i]) + " ");
		}
//		BinarySearch binarySearch = new BinarySearch();
//		int element = binarySearch.linearSearchUnsortedArray(array, k);
//		System.out.println(element);
//		
		
	}

	/**
	 * Поиск в неупорядоченном массиве
	 * 
	 * Вход: массив A[1...n], ключ k
	 * Выход: индекс i, такой что A[i] = k, или -1, если такого i нет
	 * 
	 * Время работы: O(n)
	 */
	
	private static int linearSearchUnsortedArray(int[] array, int k) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == k) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Поиск в упорядоченном массиве
	 * 
	 * Вход: упорядоченный массив A[1...n] (A[1] <= A[2] <=...<=A[n]), ключ k
	 * Выход: индекс i, такой что A[i] = k, или -1, если такого i нет
	 * 
	 * Время работы: O(log n)
	 */
	private static int linearSearchSortedArray(int[] array, int k) {
		int left = 0;
		int right = array.length - 1;
		while(left <= right) {
			//int middle = (left + right) / 2;
			int middle = left + ( right - left) / 2;
			if(array[middle] == k) {
				return middle + 1;
			} else if (array[middle] > k) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}
	
}
