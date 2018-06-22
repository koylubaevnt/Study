package com.koylubaevnt.algorithms.sorting.client;

import java.util.Scanner;

import com.koylubaevnt.algorithms.fundamentals.Stack;
import com.koylubaevnt.algorithms.fundamentals.utils.Transaction;
import com.koylubaevnt.algorithms.sorting.queue.MinPriorityQueue;

public class TopM {

	public static void main(String[] args) {
		int M = Integer.parseInt(args[0]);
		MinPriorityQueue<Transaction> pq = new MinPriorityQueue<Transaction>(M + 1);
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			pq.insert(new Transaction(scanner.nextLine()));
			if (pq.size() > M) {
				pq.delete();
			}
		}
		Stack<Transaction> stack = new Stack<Transaction>();
		while (!pq.isEmpty()) {
			stack.push(pq.delete());
		}
		for (Transaction t : stack) {
			System.out.println(t);
		}
	}

}
