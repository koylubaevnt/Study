package com.hackerrank.datastructures.stacks;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class MaximumElement {

	/**
	 * You have an empty sequence, and you will be given <code>N</code> queries. Each query is one 
	 * of these three types:
	 * 1 x  -Push the element x into the stack.
	 * 2    -Delete the element present at the top of the stack.
	 * 3    -Print the maximum element in the stack.
	 * 
	 * Input Format
	 * The first line of input contains an integer, <code>N</code>. The next <code>N</code> lines each 
	 * contain an above mentioned query. (It is guaranteed that each query is valid.)
	 * 
	 * Constraints
	 * 1 <= N <= 10^5
	 * 1 <= x <= 10^9
	 * 1 <= type <= 3
	 * 
	 * Output Format
	 * For each type <code>3</code> query, print the maximum element in the stack on a new line.
	 * 
	 * Sample Input
10
1 97
2
1 20
2
1 26
1 20
2
3
1 91
3
	 * Sample Output
	 * 26
	 * 91
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int type;
        int x;
        int max = Integer.MIN_VALUE;
        Deque<Element> stack = new LinkedList<>(); 
		for(int i =0; i < n; i++) {
            type = scanner.nextInt();
            if(type == 1) {
                x = scanner.nextInt();
                max = Math.max(x, max);
                stack.push(new Element(x, max));
            } else if(type == 2) {
            	if(!stack.isEmpty()) {
            		stack.pop();
            	}
            	if(!stack.isEmpty()) {
                    max = stack.peek().max;
            	} else {
                    max = Integer.MIN_VALUE;
                }
            } else {
            	System.out.println(stack.peek().max);
            }
        }
    }
	
	private static class Element {
		int value;
		int max;
		
		public Element(int value, int max) {
			this.value = value;
			this.max = max;
		}
	}
	
}
