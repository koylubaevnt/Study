package com.hackerrank.datastructures.arrays;

import java.util.Scanner;

public class SparseArrays {
	
	/**
	 * There are <codde>N</code> strings. Each string's length is no more than <code>20</code> 
	 * characters. There are also <code>Q</code> queries. For each query, you are given a string, 
	 * and you need to find out how many times this string occurred previously.
	 * 
	 * Input Format
	 * The first line contains <codde>N</code>, the number of strings.
	 * The next <codde>N</code> lines each contain a string.
	 * The <codde>N + 2^nd</code>line contains <codde>Q</code>, the number of queries.
	 * The following <codde>Q</code> lines each contain a query string.
	 * 
	 * Constraints
	 * 1 <= N <= 1000
	 * 1 <= Q <= 1000
	 * 1 <= length of any string <= 20
	 * 
	 * Sample Input
4
aba
baba
aba
xzxb
3
aba
xzxb
ab
	 * Sample Output
	 * 2
	 * 1
	 * 0
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String[] lines = new String[n];
		scanner.nextLine();
		for(int i = 0; i < n; i++) {
			lines[i] = scanner.nextLine();
		}
		int q = scanner.nextInt();
		String queryString;
		StringBuilder sb = new StringBuilder();
		scanner.nextLine();
		for(int i = 0; i < q; i++) {
			queryString = scanner.nextLine();
			int count = 0;
			for(int j = 0; j < n; j++) {
				if(lines[j].equals(queryString)) {
					count++;
				}
			}
			sb.append(count).append(System.lineSeparator());
		}
		System.out.println(sb.toString());
	}
}
