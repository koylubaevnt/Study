package com.koylubaevnt.stepik.csc.algorithms.dynamicprogramming;

import java.util.Scanner;

public class LongestIncreasingSubsequence {
	/*
	15
	7 2 1 3 8 4 9 1 2 6 5 9 3 8 1
	1 1 1 2 3 3 4 1 2 4 4 5 3 5 1
	-1 -1 -1 2 4 4 5 - 1 3 6 6 10 9 10 -1
	*/
	public static void main(String[] args) {
		
		new LongestIncreasingSubsequence().run();
		
	}
	
	private void run() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int a[] = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		//System.out.println(lisDividedBottomUp(a));
		
		//int ans = lisBottomUp2(a);
		int ans = ldsBottomUp2(a);
		System.out.println(ans);
		int[] l = new int[ans];
		int k = 0;
		for(int i = 1; i < n; i++) {
			if(d[i] > d[k]) {
				k = i;
			}
		}
		int j = ans - 1;
		while(k > 0) {
			l[j] = k;
			j--;
			k = prev[k];
		}
		
		for(int i = 0; i < prev.length; i++) {
			System.out.print((prev[i] + 1) + " ");
		}
		System.out.println();
		
		for(int i = 0; i < l.length; i++) {
			System.out.print((l[i] + 1) + " ");
		}
		System.out.println();
		for(int i = 0; i < l.length; i++) {
			System.out.print(a[l[i]] + " ");
		}
		System.out.println();
		scanner.close();
	}

	private int[] prev;
	private int[] d;
	
	/**
	 * Длинна возрастающей последовательности
	 * 
	 * Время работы: O(n^2)
	 * 
	 */
	private int lisBottomUp(int[] a) {
		d = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			d[i] = 1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && d[j] + 1 > d[i]) {
					d[i] = d[i] + 1;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < a.length; i++) {
			ans = Math.max(ans, d[i]);
		}
		return ans;
	}
	
	/**
	 * Длинна и сама возрастающая последовательность
	 * 
	 * Время работы: O(n^2)
	 * 
	 */
	
	private int lisBottomUp2(int[] a) {
		d = new int[a.length];
		prev = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			d[i] = 1;
			prev[i] = -1;
			for(int j = 0; j < i; j++) {
				if(a[j] < a[i] && d[j] + 1 > d[i]) {
					d[i] = d[i] + 1;
					prev[i] = j;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < a.length; i++) {
			ans = Math.max(ans, d[i]);
		}
		return ans;
	}
	
	
	/**
	 * Задача на программирование: наибольшая последовательнократная подпоследовательность
	 * 
	 * Дано целое число 1 ≤ n ≤ 10^3 и массив A[1…n] натуральных чисел, не 
	 * превосходящих 2⋅10^9. Выведите максимальное 1 ≤ k ≤ n, для которого 
	 * найдётся подпоследовательность 1 ≤ i1 < i2 < … < ik ≤ n длины k, в 
	 * которой каждый элемент делится на предыдущий (формально: для  всех 
	 * 1 ≤ j < k, A[ij] | A[ij + 1]).
	 * 
	 *   Sample Input:
4
3 6 7 12
	 *   Sample Output:
	 *   3
	 *   
	 */
	private int lisDividedBottomUp(int[] a) {
		d = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			d[i] = 1;
			for(int j = 0; j < i; j++) {
				if((a[i] % a[j] == 0) && d[j] + 1 > d[i]) {
					d[i] = d[i] + 1;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < a.length; i++) {
			ans = Math.max(ans, d[i]);
		}
		return ans;
	}
	
	
	/**
	 * Задача на программирование повышенной сложности: наибольшая невозрастающая подпоследовательность
	 * 
	 * Дано целое число 1 ≤ n ≤ 10^5 и массив A[1…n], содержащий неотрицательные целые числа, не превосходящие 10^9. 
	 * Найдите наибольшую невозрастающую подпоследовательность в A. В первой строке выведите её длину k, во второй — 
	 * её индексы 1 ≤ i1 < i2 < … < ik ≤n (таким образом, A[i1] ≥ A[i2] ≥ … ≥ A[in]
	 * 
	 * Sample Input:
5
5 3 4 4 2
	 * Sample Output:
	 * 4
	 * 1 3 4 5 
	 * 
	 */
	
	private int ldsBottomUp2(int[] a) {
		d = new int[a.length];
		prev = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			d[i] = 1;
			prev[i] = -1;
			for(int j = 0; j < i; j++) {
				if(a[j] >= a[i] && d[j] + 1 > d[i]) {
					d[i] = d[i] + 1;
					prev[i] = j;
				}
			}
		}
		int ans = 0;
		for(int i = 0; i < a.length; i++) {
			ans = Math.max(ans, d[i]);
		}
		return ans;
	}
	
	
}
