package com.hackerrank.datastructures.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SwapNodes {

	/**
	 * Swap operation: Given a tree and a integer, K, we have to swap the subtrees of all the nodes who are at depth h, where 
	 * h ∈ [K, 2K, 3K,...].
	 * You are given a tree of N nodes where nodes are indexed from [1..N] and it is rooted at 1. You have to perform T swap operations 
	 * on it, and after each swap operation print the inorder traversal of the current state of the tree.
	 * 
	 * Input Format
	 * First line of input contains N, number of nodes in tree. Then N lines follow. Here each of ith line (1 <= i <= N) contains two 
	 * integers, a b, where a is the index of left child, and b is the index of right child of ith node. -1 is used to represent null 
	 * node.
	 * Next line contain an integer, T. Then again T lines follows. Each of these line contains an integer K.
	 * 
	 * Output Format
	 * For each K, perform swap operation as mentioned above and print the inorder traversal of the current state of tree.
	 * 
	 * Constraints
	 * 1 <= N <= 1024
	 * 1 <= T <= 100
	 * 1 <= K <= N
	 * Either a = -1 or 2 <= a <= N
	 * Either b = -1 or 2 <= b <= N
	 * Index of (non-null) child will always be greater than that of parent.
	 * 
	 * Sample Input #00
3
2 3
-1 -1
-1 -1
2
1
1
	 * Sample Output #00
	 * 3 1 2
	 * 2 1 3
	 * 
	 * Sample Input #01
5
2 3
-1 4
-1 5
-1 -1
-1 -1
1
2
	 * Sample Output #01
	 * 4 2 1 5 3
	 * 
	 * Sample Input #02
11
2 3
4 -1
5 -1
6 -1
7 8
-1 9
-1 -1
10 11
-1 -1
-1 -1
-1 -1
2
2
4
	 * Sample Output #02
	 * 2 9 6 4 1 3 7 5 11 8 10
	 * 2 6 9 4 1 3 7 5 10 8 11
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();//number of nodes
		Node root = new Node(5, 1);

		Node leftNode;
		Node rightNode;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		for(int i = 1; i <= n; i++) {
			Node current = queue.remove();
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			if(a == -1) {
				leftNode = null; 
			} else {
				leftNode = new Node(a, current.depth + 1);
			}
			if(b == -1) {
				rightNode = null; 
			} else {
				rightNode = new Node(b, current.depth + 1);
			}
			current.left = leftNode;
			current.right = rightNode;
			if(leftNode != null) {
				queue.add(leftNode);
			}
			if (rightNode != null) {
				queue.add(rightNode);
			}		
		}
		printTree(root);
		System.out.println();
		int t = scanner.nextInt();//number of swap operation
		for(int i = 0; i < t; i++) {
			int swap = scanner.nextInt();
			swapNode(root, swap);
			printTree(root);
			System.out.println();
		}
	}
	
	private static void swapNode(Node head, int level) {
		if(head == null) {
			return;
		}
		
		if(head.depth % level == 0) {
			Node temp = head.right;
			head.right = head.left;
			head.left = temp;
		}
		swapNode(head.left, level);
		swapNode(head.right, level);
	}
	
	private static void printTree(Node head) {
		if(head == null) {
			return;
		}
		printTree(head.left);
		System.out.print(head.index + "(" + head.depth + ") ");
		printTree(head.right);
	}

	static class Node {
		int index;
		int depth;
		Node left;
		Node right;
		
		public Node() {
			
		}
		
		public Node(int index, int depth) {
			this(index, depth, null, null);
		}
		
		public Node(int index, int depth, Node left, Node right) {
			this.index = index;
			this.depth = depth;
			this.left = left;
			this.right = right;
		}
	}
}
