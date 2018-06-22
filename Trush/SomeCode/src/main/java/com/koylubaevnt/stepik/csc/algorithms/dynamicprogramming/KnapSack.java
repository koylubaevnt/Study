package com.koylubaevnt.stepik.csc.algorithms.dynamicprogramming;

import java.util.Scanner;

public class KnapSack {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int W = scanner.nextInt();
		int n = scanner.nextInt();
		
		int[] mas = new int[n + 1];
		for(int i = 1; i <= n; i++) {
			mas[i] = scanner.nextInt();
		}
		int[][] D = new int[W + 1][n + 1];
		
		for(int w = 0; w <= W; w++) {
			D[w][0] = 0;
		}
		
		for(int i = 0; i <= n; i++) {
			D[0][i] = 0;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int w = 1; w <= W; w++) {
				D[w][i] = D[w][i - 1];
				if(mas[i] <= w) {
					D[w][i] = Math.max(D[w][i], D[w - mas[i]][i - 1] + mas[i]);
				}
			}
		}
		System.out.println(D[W][n]);
		scanner.close();
	}
	
}
