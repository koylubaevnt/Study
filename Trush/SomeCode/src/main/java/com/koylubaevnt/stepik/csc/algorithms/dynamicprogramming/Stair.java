package com.koylubaevnt.stepik.csc.algorithms.dynamicprogramming;

import java.util.Scanner;

/**
 * Задача на программирование: лестница
 * 
 * Даны число 1 ≤ n ≤ 10^2 ступенек лестницы и целые числа −10^4 ≤ a1, …, an ≤ 10^4, которыми помечены ступеньки. Найдите 
 * максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки), каждый 
 * раз поднимаясь на одну или две ступеньки.
 * 
 * Sample Input 1:
2
1 2
Sample Output 1:
3
Sample Input 2:
2
2 -1
Sample Output 2:
1
Sample Input 3:
3
-1 2 1
Sample Output 3:
3
 * @author koylu
 *
 */
public class Stair {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}
		int i = n - 1;
		int sum = a[i];
		i--;
		if(i == 0) {
			if(a[0] > 0) {
				sum += a[0];
			}
		} else {
			while(i >= 1) {
				if(a[i] < 0 && a[i - 1] < 0) {
					if(a[i] > a[i - 1]) {
						sum += a[i];
					} else {
						sum += a[i - 1];
						i--;
					}
				} else if(a[i] < 0) {
					sum += a[i - 1];
					i--;
					
				} else if(a[i - 1] < 0) {
					sum += a[i];
				} else {
					sum += a[i];
					if(i == 1 && a[0] > 0) {
						sum += a[0];
						//break;
					}
				}
				i--;
				
			}
		}
		
		System.out.println(sum);
		scanner.close();
	}

}
