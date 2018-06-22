package com.koylubaevnt.algorithms.search;

import com.koylubaevnt.algorithms.fundamentals.Queue;
import com.koylubaevnt.algorithms.search.interfaces.SymbolTable;

/**
 * Последовательный поиск в неупорядоченном массиве
 * 
 * @author KojlubaevNT
 *
 * @param <Key>
 *            Ключ
 * @param <Value>
 *            Значение
 */
public class SequentialSearch<Key extends Comparable<Key>, Value> extends SymbolTable<Key, Value> {

	private class Node {
		Key key;
		Value value;
		Node next;

		public Node(Key key, Value value, Node next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	private Node first;
	private int n;

	public void put(Key key, Value value) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.value = value;
				return;
			}
		}
		first = new Node(key, value, first);
		n++;
	}

	@Override
	public void delete(Key key) {
		first = delete(first, key);
	}

	private Node delete(Node node, Key key) {
		if (node == null)
			return null;
		if (key.equals(node.key)) {
			n--;
			return node.next;
		}
		node.next = delete(node.next, key);
		return node;
	}

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				return x.value;
			}
		}
		return null;
	}

	public int size() {
		return n;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next) {
			queue.enqueue(x.key);
		}
		return queue;
	}

	public static void main(String[] args) {
		SequentialSearch<String, Integer> st = new SequentialSearch<String, Integer>();

		/*
		 * for (int i = 0; !StdIn.isEmpty(); i++) { String key =
		 * StdIn.readString(); st.put(key, i); } for (String s : st.keys())
		 * StdOut.println(s + " " + st.get(s));
		 */
	}
}
