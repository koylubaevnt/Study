package com.koylubaevnt.stepik.csc.algorithms.dynamicprogramming;

import java.util.Scanner;

public class EditDistance {

	
	public static void main(String[] args) {
		new EditDistance().run();
	}

	private String a;
	private String b;
	
	private int[] current;
	private int[] previous;
	
	private void run() {
		Scanner scanner = new Scanner(System.in);
		a = scanner.nextLine();
		b = scanner.nextLine();
		
		System.out.println(editDistBU(a, b));
		
		scanner.close();
	}
	
	private int editDistBU(String a, String b) {
		int m = a.length();
		int n = b.length();
		
		current = new int[n + 1];
		
		for(int i = 0; i <= n; i++) {
			current[i] = i;
		}
		
		for(int i = 1; i <= m; i++) {
			previous = current;
			current = new int[n + 1];
			for(int j = 0; j <= n; j++) {
				if (j == 0) {
					current[j] = i;
				} else {
					int cost = (a.charAt(i - 1) != b.charAt(j - 1)) ? 1 : 0;
					if(current[j - 1] < previous[j] && current[j - 1] < previous[j - 1] + cost) {
						current[j] = current[j - 1] + 1;
					} else if(previous[j] < previous[j - 1] + cost) {
						current[j] = previous[j] + 1;
					} else {
						current[j] = previous[j - 1] + cost;
					}
				}
			}
		}
		return current[n];
	}
	
}
