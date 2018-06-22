package com.koylubaevnt.algorithms.search;

import com.koylubaevnt.algorithms.fundamentals.Queue;
import com.koylubaevnt.algorithms.search.interfaces.OrderedSymbolTable;

/**
 * Дерево бинарного поиска
 * 
 * @author KojlubaevNT
 *
 * @param <Key>		Ключ
 * @param <Value>	Знаечние
 */
public class BST<Key extends Comparable<Key>, Value> extends OrderedSymbolTable<Key, Value> {

	private class Node {
		private Key key;		//Ключ
		private Value value;	//Значение
		private Node left;		//левая ссылка на поддерево
		private Node right;		//правая ссылка на поддерево
		private int n;			//количество узлов в поддереве с этим корнем
		
		public Node(Key key, Value value, int n) {
			this.key = key;
			this.value = value;
			this.n = n;
		}	
	}
	
	private Node root;		//Корень ДБП

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(queue, root, lo, hi);
		return queue;
	}

	private void keys(Queue<Key> queue, Node node, Key lo, Key hi) {
		if(node == null) {
			return;
		}
		int cmpLo = lo.compareTo(node.key);
		int cmpHi = hi.compareTo(node.key);
		if(cmpLo < 0) {
			keys(queue, node.left, lo, hi);
		}
		if(cmpLo <= 0 && cmpHi >= 0) {
			queue.enqueue(node.key);
		}
		if(cmpHi > 0) {
			keys(queue, node.right, lo, hi);
		}
	}
	
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node node) {
		if(node.left == null) {
			return node;
		}
		return min(node.left);
	}

	public Key max() {
		return max(root).key;
	}

	private Node max(Node node) {
		if(node.right == null) {
			return node;
		}
		return max(node.right);
	}

	public Key ceiling(Key key) {
		Node node = ceiling(root, key);
		if(node == null) {
			return null;
		}
		return node.key;
	}

	private Node ceiling(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.key);
		if(cmp == 0) {
			return node;
		}
		if(cmp < 0) {
			Node temp = ceiling(node.left, key);
			if(temp != null) {
				return temp;
			} else {
				return node;
			}
		}
		return ceiling(node.right, key);
	}
	
	public Key floor(Key key) {
		Node node = floor(root, key);
		if(node == null) {
			return null;
		}
		return node.key;
	}
	
	private Node floor(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.key);
		if(cmp == 0) {
			return node;
		}
		if(cmp < 0) {
			return floor(node.left, key);
		} 
		Node temp = floor(node.right, key);
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
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			return rank(node.left, key);
		} else if (cmp > 0) {
			return 1 + size(node.left) + rank(node.right, key);
		} else {
			return size(node.left);
		}
	}
	
	public Key select(int key) {
		Node node = select(root, key);
		return node == null? null : node.key;
	}

	private Node select(Node node, int key) {
		if(node == null) {
			return null;
		}
		int t = size(node.left);
		if(t > key) {
			return select(node.left, key);
		} else if(t < key) {
			return select(node.right, key - t - 1);
		} else {
			return node;
		}
	}

	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null) {
			node = new Node(key, value, 1);
		}
		int cmp =  key.compareTo(node.key);
		if(cmp < 0) {
			node.left = put(node.left, key, value);
		} else if(cmp > 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Value get(Key key) {
		return get(root, key);
	}
	
	private Value get(Node node, Key key) {
		if (node == null) {
			return null;
		}
		int cmp =  key.compareTo(node.key);
		if(cmp < 0) {
			return get(node.left, key);
		} else if(cmp > 0) {
			return get(node.right, key);
		} else {
			return node.value;
		}
	}

	public void delete(Key key) {
		root = delete(root, key);	
	}

	private Node delete(Node node, Key key) {
		if(node == null) {
			return null;
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			node.left = delete(node.left, key);
		} else if(cmp > 0) {
			node.right = delete(node.right, key);
		} else {
			if(node.right == null) {
				return node.left;
			}
			if(node.left == null) {
				return node.right;
			}
			Node temp = node;
			node = min(temp.right);
			node.right = deleteMin(temp.right);
			node.left = temp.left;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if(node == null) {
			return 0;
		} else {
			return node.n;
		}
	}
	
	@Override
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if(node.left == null) {
			return node.right;
		}
		node.left = deleteMin(node.left);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	@Override
	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if(node.right == null) {
			return node.left;
		}
		node.right = deleteMin(node.right);
		node.n = size(node.left) + size(node.right) + 1;
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
		
		BST<String, Integer> bst = new BST<String, Integer>();
		
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
