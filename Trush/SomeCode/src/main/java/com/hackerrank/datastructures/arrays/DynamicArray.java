package com.hackerrank.datastructures.arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DynamicArray {

	/**
	 * Task
	 * 
	 * Given N, Q, and Q queries, execute each query.
	 * 
	 * Note: O+ is the bitwise XOR operation, which corresponds to the ^ 
	 * operator in most languages. Learn more about it on Wikipedia.
	 *  
	 * Input Format
	 * 
	 * The first line contains two space-separated integers, N (the number 
	 * of sequences) and Q (the number of queries), respectively.
	 * Each of the Q subsequent lines contains a query in the format defined 
	 * above.
	 * 
	 * Constraints
	 * 1<= N, Q <= 10^5
	 * 0 <= x <= 10^9
	 * 0 <= y <= 10^9
	 * It is guaranteed that query type will never query an empty sequence or 
	 * index.
	 * 
	 * Output Format
	 * 
	 * For each type 2 query, print the updated value of <code>lastAns</code> on a new line.
2 5
1 0 5
1 1 7
1 0 3
2 1 0
2 1 1

	 * @param args
	 */
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int q = scanner.nextInt();
		scanner.nextLine();
		List<List<Integer>> lists = new ArrayList<>(q);
		
		Integer lastAns = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			String[] tokens = scanner.nextLine().split(" ");
			int type = Integer.parseInt(tokens[0]);
			int x = Integer.parseInt(tokens[1]);
			int y = Integer.parseInt(tokens[2]);
			int seq = (x ^ lastAns) % n;
			if(type == 1) {
				if(lists.size() <= seq) {
					List<Integer> list = new ArrayList<>();
					list.add(y);
					while(lists.size() <= seq) {
						lists.add(lists.size(), null);
				    }
					lists.set(seq, list);
				} else {
					List<Integer> list = lists.get(seq);
					if(list == null) {
						list = new ArrayList<>();
					}
					list.add(y);
					lists.set(seq, list);
				}
				
			} else {
				if(lists.get(seq) != null && lists.get(seq).get(y % lists.get(seq).size()) != null) {
					lastAns = lists.get(seq).get(y % lists.get(seq).size());
				}
				sb.append(lastAns).append(System.lineSeparator());
			}
		}
		System.out.println(sb.toString());
		
		
	}

}
