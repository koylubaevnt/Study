package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.util.Scanner;

public class CountInversion {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Long[] array = new Long[n];
		for(int i = 0; i < n; i++) {
			array[i] = scanner.nextLong();
		}
		System.out.println(countInversion(array));
		scanner.close();
	}

	/**
	 * Задача на программирование: число инверсий
	 * 
	 * Первая строка содержит число 1 ≤ n ≤ 10^5, вторая — массив A[1…n], содержащий натуральные 
	 * числа, не превосходящие 10^9. Необходимо посчитать число пар индексов 1 ≤ i < j ≤ n, для которых 
	 * A[i] > A[j]. (Такая пара элементов называется инверсией массива. Количество инверсий в массиве 
	 * является в некотором смысле его мерой неупорядоченности: например, в упорядоченном по 
	 * неубыванию массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию, инверсию 
	 * образуют каждые два элемента.)
	 * 
	 * Sample Input:
		5
		2 3 9 2 9
	 * 
	 * Sample Output:
	 * 2
	 * 
	 * Sample Input:
		 7
		 7 6 5 4 3 2 1
	 * 
	 * Sample Output:
	 * 21
	 * 	 
	 * Sample Input:
	 * 5
	 * 1 2 3 5 4
	 * 
	 * Sample Output:
	 * 1
	 * 	 
	 * Sample Input:
	 * 6
	 * 1 3 4 5 6 2
	 * Sample Output:
	 * 4 
	 */
	public static long countInversion(Long[] a) {
		long countInversion = sort(a); 
		return countInversion;
	}
	
	private static Comparable[] array; //Дополнительный мссив
	
	public static long sort(Comparable[] a) {
		int n = a.length;
		long count = 0;
		array = new Comparable[n];
		for (int sz = 1; sz < n; sz = sz + sz) {
			for(int lo = 0; lo < n - sz; lo += sz + sz) {
				count += merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
			}
		}
		return count;
	}
	
	private static long merge(Comparable[] a, int lo, int mid, int hi) {
		long count = 0;
		int i = lo;
		int j = mid + 1;
		//Копируем массив a в array (чтобы потом сравнивая 2 элемента в массиве array выбрать меньший)
		for(int k = lo; k <= hi; k++) {
			array[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = array[j++];
			} else if (j > hi) {
				a[k] = array[i++];
			} else if (less(array[j], array[i])) {
				a[k] = array[j++];
				count = count + (mid - i) + 1;
			} else {
				a[k] = array[i++];
			}
		}
		return count;
	}
	
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
}
