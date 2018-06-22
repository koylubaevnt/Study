package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CountSorting {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] readString = br.readLine().split(" ");
		
		int n = Integer.parseInt(readString[0]);
		readString = br.readLine().split(" ");
		int[] a = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(readString[i]);
		}
		a = Sorting.countSorting(a, 10);
		StringBuilder sb = new StringBuilder(n + n);
		for(int i = 0; i < n; i++) {
			sb.append(a[i]).append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}
/*
5
2 3 9 2 9
*/	
}
