package com.koylubaevnt.algorithms.search;

import java.util.NoSuchElementException;

import com.koylubaevnt.algorithms.fundamentals.Queue;
import com.koylubaevnt.algorithms.search.interfaces.OrderedSymbolTable;

public class RedBlackBST<Key extends Comparable<Key>, Value> extends OrderedSymbolTable<Key, Value> {

	private static enum Color { RED, BLACK};
	private Node root;
	
	private class Node {

		private Key key;						//Ключ
		private Value value;					//Значение
		private Node left;						//левая ссылка на поддерево
		private Node right;						//правая ссылка на поддерево
		private int n;							//количество узлов в поддереве с этим корнем
		private Color color;					//Цвет ссылки на данный узел 
		
		public Node(Key key, Value value, int n, Color color) {
			this.key = key;
			this.value = value;
			this.n = n;
			this.color = color;
		}

		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
		}

		public Value getValue() {
			return value;
		}

		public void setValue(Value value) {
			this.value = value;
		}

		public Node getLeft() {
			return left;
		}

		public void setLeft(Node left) {
			this.left = left;
		}

		public Node getRight() {
			return right;
		}

		public void setRight(Node right) {
			this.right = right;
		}

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Key: ")
				.append(key.toString())
				.append(", Value: ")
				.append(value.toString());
			return sb.toString();
		}
		
	}
	
	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(queue, root, lo, hi);
		return queue;
	}

	private void keys(Queue<Key> queue, Node node, Key lo, Key hi) {
		if(node == null) {
			return;
		}
		int cmpLo = lo.compareTo(node.getKey());
		int cmpHi = hi.compareTo(node.getKey());
		if(cmpLo < 0) {
			keys(queue, node.getLeft(), lo, hi);
		}
		if(cmpLo <= 0 && cmpHi >= 0) {
			queue.enqueue(node.getKey());
		}
		if(cmpHi > 0) {
			keys(queue, node.getRight(), lo, hi);
		}
	}

	public Key min() {
		return min(root).getKey();
	}

	private Node min(Node node) {
		if(node.getLeft() == null) {
			return node; 
		} else {
			return min(node.getLeft());
		}
	}
	
	public Key max() {
		return max(root).getKey();
	}

	private Node max(Node node) {
		if(node.getRight() == null) {
			return node; 
		} else {
			return min(node.getRight());
		}
	}
	
	public Key ceiling(Key key) {
		Node node = ceiling(root, key);
		if(node == null) {
			return null;
		}
		return node.getKey();
	}

	private Node ceiling(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.getKey());
		if(cmp == 0) {
			return node;
		}
		if(cmp < 0) {
			Node temp = ceiling(node.getLeft(), key);
			if(temp != null) {
				return temp;
			} else {
				return node;
			}
		}
		return ceiling(node.getRight(), key);
	}

	public Key floor(Key key) {
		Node node = floor(root, key);
		if(node == null) {
			return null;
		}
		return node.getKey();
	}

	private Node floor(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.getKey());
		if(cmp == 0) {
			return node;
		}
		if(cmp < 0) {
			return floor(node.getLeft(), key);
		} 
		Node temp = floor(node.getRight(), key);
		if(temp != null) {
			return temp;
		} else {
			return node;
		}
	}
	
	public int rank(Key key) {
		return rank(root, key);
	}

	private int rank(Node node, Key key) {
		if(node == null) {
			return 0;
		}
		int cmp = key.compareTo(node.getKey());
		if(cmp < 0) {
			return rank(node.getLeft(), key);
		} else if (cmp > 0) {
			return 1 + size(node.getLeft()) + rank(node.getRight(), key);
		} else {
			return size(node.getLeft());
		}
	}

	public Key select(int key) {
		Node node = select(root, key);
		return node == null? null : (Key) node.getKey();
	}

	private Node select(Node node, int key) {
		if(node == null) {
			return null;
		}
		int t = size(node.getLeft());
		if(t > key) {
			return select(node.getLeft(), key);
		} else if(t < key) {
			return select(node.getRight(), key - t - 1);
		} else {
			return node;
		}
	}

	public void put(Key key, Value value) {
		if(value == null) {
			delete(key);
			return;
		}
		
		root = put(root, key, value);
		root.setColor(Color.BLACK);
	}

	public Node put(Node node, Key key, Value value) {
		if(node == null) {
			//Вставка с красной связью
			return new Node(key, value, 1, Color.RED);
		}
		int cmp = key.compareTo(node.getKey());
		if(cmp < 0) {
			node.setLeft(put(node.getLeft(), key, value));
		} else if(cmp > 0) {
			node.setRight(put(node.getRight(), key, value));
		} else {
			node.setValue(value);
		}
		if(isRed(node.getRight()) && !isRed(node.getLeft())) {
			node = rotateLeft(node);
		}
		if(isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
			node = rotateRight(node);
		}
		if(isRed(node.getLeft()) && isRed(node.getRight())) {
			flipColors(node);
		}
		node.setN(size(node.getLeft()) + size(node.getRight()) + 1);
		return node;
	}
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.getKey());
		if(cmp < 0) {
			return get(node.getLeft(), key);
		} else if(cmp > 0) {
			return get(node.getRight(), key);
		} else {
			return node.getValue();
		}
		/*
		while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
		 */
	}
	
	
	
	@Override
	public void deleteMin() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// Если оба потомка для корня - ЧЕРНЫЕ, то для родителя ставим цвет - КРАСНЫЙ
		if(!isRed(root.getLeft()) && !isRed(root.getRight())) {
			root.setColor(Color.RED);
		}
		
		root = deleteMin(root);
		if(!isEmpty()) {
			root.setColor(Color.BLACK);
		}
	}

	private Node deleteMin(Node node) {
		if(node.getLeft() == null) {
			return null;
		}
		if(!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft())) {
			node = moveRedLeft(node);
		}
		node.setLeft(deleteMin(node.getLeft()));
		return balance(node);
	}
	
	@Override
	public void deleteMax() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		// Если оба потомка для корня - ЧЕРНЫЕ, то для родителя ставим цвет - КРАСНЫЙ
		if(!isRed(root.getLeft()) && !isRed(root.getRight())) {
			root.setColor(Color.RED);
		}
		
		root = deleteMax(root);
		if(!isEmpty()) {
			root.setColor(Color.BLACK);
		}
	}

	private Node deleteMax(Node node) {
		if(isRed(node.getLeft())) {
			node = rotateRight(node);
		}
		
		if(node.getRight() == null) {
			return null;
		}
		
		if(!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
			node = moveRedRight(node);
		}
		node.setRight(deleteMax(node.getRight()));
		return balance(node);
	}
	
	public void delete(Key key) {
		if(!contains(key)) {
			return;
		}
		if(!isRed(root.getLeft()) && !isRed(root.getRight())) {
			root.setColor(Color.RED);
		}
		
		root = delete(root, key);
		if(!isEmpty()) {
			root.setColor(Color.BLACK);
		}
	}

	private Node delete(Node node, Key key) {
		int cmp = key.compareTo(node.getKey());
		if (cmp < 0) {
			if(!isRed(node.getLeft()) && !isRed(node.getLeft().getLeft())) {
				node = moveRedLeft(node);
			}
			node.setLeft(delete(node.getLeft(), key));
		} else {
			if(isRed(node.getLeft())) {
				node = moveRedLeft(node);
			}
			if(cmp == 0 && node.getRight() == null) {
				return null;
			}
			if(!isRed(node.getRight()) && !isRed(node.getRight().getLeft())) {
				node = moveRedRight(node);
			}
			if(cmp == 0) {
				Node x = min(node.getRight());
				node.setKey(x.getKey());
				node.setValue(x.getValue());
				node.setRight(deleteMin(node.getRight()));
			} else {
				node.setRight(delete(node.getRight(), key));
			}
		}
		return balance(node);
	}
	
	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		} else {
			return node.getN();
		}
	}
	
	

	private boolean isRed(Node node) {
		return node == null ? false : node.getColor().equals(Color.RED);
	}
	
	private Node rotateLeft(Node head) {
		Node node = head.getRight();
		head.setRight(node.getLeft());
		node.setLeft(head);
		node.setColor(head.getColor());
		head.setColor(Color.RED);
		node.setN(head.getN());
		head.setN(1 + size(head.getLeft()) + size(head.getRight()));
		return node;
	}
	
	private Node rotateRight(Node head) {
		Node node = head.getLeft();
		head.setLeft(node.getRight());
		node.setRight(head);
		node.setColor(head.getColor());
		head.setColor(Color.RED);
		node.setN(head.getN());
		head.setN(1 + size(head.getLeft()) + size(head.getRight()));
		return node;
	}
	
	private void flipColors(Node head) {
		head.setColor(Color.RED);
		head.getLeft().setColor(Color.BLACK);
		head.getRight().setColor(Color.BLACK);
		
	}
	
	private Node moveRedLeft(Node node) {
		flipColors(node);
		if (isRed(node.getRight().getLeft())) {
			node.setRight(rotateRight(node.getRight()));
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}
	
	private Node moveRedRight(Node node) {
		
		flipColors(node);
		if (isRed(node.getLeft().getLeft())) {
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}
	
	private Node balance(Node node) {
		if (isRed(node.getRight())) {
			node = rotateLeft(node);
		}
		if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
			node = rotateRight(node);
		}
		if (isRed(node.getLeft()) && isRed(node.getRight())) {
			flipColors(node);
		}
		node.setN(size(node.getLeft()) + size(node.getRight()) + 1);
		return node;
		
	}
	
	public void print() {
		print(root);
	}
	
	private void print(Node node) {
		if(node == null) {
			return;
		}
		print(node.left);
		System.out.print(node.key + " ");
		print(node.right);
	}
	
	public static void main(String[] args) {
		String[] str = new String[] {
				//"S","E","A","R","C","H","E","X","A","M","P","L","E"
				"S","E","A","R","C","H","X","M","P"
				};
		
		RedBlackBST<String, Integer> bst = new RedBlackBST<String, Integer>();
		
		for(int i = 0; i < str.length; i++) {
			bst.put(str[i], i);
		}
		bst.print();
		System.out.println();
		System.out.println(bst.get("R"));
		System.out.println(bst.get("T"));
		bst.print();
		System.out.println();
		bst.put("L", 20);
		bst.print();
		System.out.println();
		System.out.println(bst.floor("G"));
		System.out.println(bst.ceiling("G"));
		System.out.println(bst.max());
		System.out.println(bst.min());
		System.out.println(bst.select(3));
		bst.print();
		System.out.println();
		bst.deleteMin();
		bst.print();
		System.out.println();
		
		bst.print();
		System.out.println();
		bst.deleteMax();
		bst.print();
		System.out.println();
		
		bst.print();
		System.out.println();
		bst.delete("E");
		bst.print();
		System.out.println();
		
		
		System.out.println(bst.keys());
		System.out.println(bst.keys("L","S"));
		System.out.println(bst.keys("K","O"));
	}


}
