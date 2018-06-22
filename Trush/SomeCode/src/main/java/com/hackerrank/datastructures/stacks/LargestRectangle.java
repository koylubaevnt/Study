package com.hackerrank.datastructures.stacks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class LargestRectangle {

	/**
	 * There are <code>N</code> buildings in a certain two-dimensional landscape. Each building has a 
	 * height given by <code>hi, i =[1, N]</code>. If you join <code>K</code> adjacent buildings, 
	 * they will form a solid rectangle of area <code>K * min(hi, hi+1,...hi+k-1)</code>.
	 * Given <code>N</code> buildings, find the greatest such solid area formed by consecutive buildings.
	 * 
	 * Input Format
	 * The first line contains <code>N</code>, the number of buildings altogether.
	 * The second line contains <code>N</code> space-separated integers, each representing the height 
	 * of a building.
	 * 
	 * Constraints
	 * 1 <= N <= 10^5
	 * 1 <= hi <= 10^6
	 * 
	 * Output Format
	 * One integer representing the maximum area of rectangle formed.
	 * 
	 * Sample Input
5
1 2 3 4 5
	 * Sample Output
	 * 9
	 * 
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] h = new int[n];
		Deque<Integer> stack = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			h[i] = scanner.nextInt();			
		}
		
		int area = 0;
		int maxArea = Integer.MIN_VALUE;
		int top;
		int i;
		for(i = 0; i < h.length;) {
			if(stack.isEmpty() || h[stack.peek()] <= h[i]) {
				stack.push(i++);
			} else {
				top = stack.pop();
				if(stack.isEmpty()) {
					area = h[top] * i;
				} else {
					area = h[top] * (i - stack.peek() - 1);
				}
				if(area > maxArea) {
					maxArea = area;
				}
			}
		}
		
		while(!stack.isEmpty()) {
			top = stack.pop();
			if(stack.isEmpty()) {
				area = h[top] * i;
			} else {
				area = h[top] * (i - stack.peek() - 1);
			}
			if(area > maxArea) {
				maxArea = area;
			}
		}
		System.out.println(maxArea);
	}
	
}
