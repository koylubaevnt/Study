package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.util.Arrays;

public class Matrix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] x = new int[3][3];
		int[][] y = new int[3][3];
		
		System.out.println(Arrays.deepToString(naiveMultiply(x, y)));
	}

	/**
	 * Наивный алгоритм умножения матриц
	 * 
	 * Время работы O(n^3)
	 */
	private static int[][] naiveMultiply(int[][] x, int[][] y) {
		int n = x.length;
		int[][] m = new int[n][n]; 
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < n; j++) {
					m[i][j] += x[i][k] * y[k][j];
				}
			}	
		}
		return m;
	}
	
	/**
	 * Алгоритм Штрассена
	 * 
	 * Время работы O(n^lon2 7) = O(n^2.807)
	 * 
	 */
	
}
