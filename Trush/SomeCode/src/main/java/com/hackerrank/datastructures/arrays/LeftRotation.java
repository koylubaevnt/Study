package com.hackerrank.datastructures.arrays;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class LeftRotation {

	/**
	 * A left rotation operation on an array of size <code>n</code> shifts each of the array's 
	 * elements <code>1</code> unit to the left. For example, if left rotations are performed on 
	 * array <code>[1,2,3,4,5]</code>, then the array would become <code>[3,4,5,1,2]</code>.
	 * 
	 * Given an array of integers <code>n</code> and a number, <code>d</code>, perform left rotations 
	 * on the array. Then print the updated array as a single line of space-separated integers.
	 * 
	 * Input Format
	 * The first line contains two space-separated integers denoting the respective values 
	 * of <code>n</code>(the number of integers) and <code>d</code>(the number of left rotations you 
	 * must perform).
	 * The second line contains <code>n</code> space-separated integers describing the respective 
	 * elements of the array's initial state.
	 * 
	 * Constraints
	 * 1 <= n <= 10^5
	 * 1 <= d <= n
	 * 1 <= ai <= 10^6
	 * 
	 * Output Format
	 * Print a single line of <code>n</code> space-separated integers denoting the final state of 
	 * the array after performing <code>d</code> left rotations.
	 * 
	 * Sample Input
5 4
1 2 3 4 5
	 * Sample Output
	 * 5 1 2 3 4
	 * 
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int d = scanner.nextInt();
		Deque<Integer> l = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			l.add(scanner.nextInt());
		}
		
		for(int i = 0; i < d; i++) {
			l.addLast(l.removeFirst());
		}
		
		while(!l.isEmpty()) {
			System.out.print(l.remove() + " ");
		}
	}

}
