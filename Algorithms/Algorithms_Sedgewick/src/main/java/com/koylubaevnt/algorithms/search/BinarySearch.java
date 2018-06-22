package com.koylubaevnt.algorithms.search;

import com.koylubaevnt.algorithms.fundamentals.Queue;
import com.koylubaevnt.algorithms.search.interfaces.OrderedSymbolTable;

/**
 * Бинарный поиск в упорядоченном массиве
 * 
 * @author KojlubaevNT
 *
 * @param <Key>
 *            Ключ
 * @param <Value>
 *            Значение
 */
public class BinarySearch<Key extends Comparable<Key>, Value> extends OrderedSymbolTable<Key, Value> {

	private Key[] keys;
	private Value[] values;
	private int n;

	/**
	 * Конструктор
	 * 
	 * @param capacity
	 *            Объем для массивов
	 */
	public BinarySearch(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		for (int i = rank(lo); i < rank(hi); i++) {
			queue.enqueue(keys[i]);
		}
		if (contains(hi)) {
			queue.enqueue(keys[rank(hi)]);
		}
		return queue;
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[n - 1];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		return keys[i];
	}

	public Key floor(Key key) {
		int i = rank(key);
		if (i < n && key.compareTo(keys[i]) == 0) {
			return keys[i];
		}
		if (i == 0) {
			return null;
		}
		return keys[i - 1];
	}

	/**
	 * Возвращает количество ключей, меньших {@code key} Рекурсивный
	 * 
	 * @param key
	 *            Ключ
	 * @param lo
	 *            Нижняя граница
	 * @param hi
	 *            Верхняя граница
	 * @return Количество ключей, меньших {@code key}
	 */
	private int rank(Key key, int lo, int hi) {
		if (hi < lo) {
			return lo;
		}

		int mid = lo + (hi - lo) / 2;
		int cmp = key.compareTo(keys[mid]);
		if (cmp < 0) {
			return rank(key, lo, mid - 1);
		} else if (cmp > 0) {
			return rank(key, mid + 1, hi);
		} else {
			return mid;
		}
	}

	public int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) {
				hi = mid - 1;
			} else if (cmp > 0) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return lo;
	}

	public Key select(int key) {
		if (key > n || key < 0) {
			return null;
		}
		return keys[key];
	}

	public void put(Key key, Value value) {
		if (value == null) {
			delete(key);
			return;
		}

		int i = rank(key);

		if (i < n && keys[i].compareTo(key) == 0) {
			values[i] = value;
			return;
		}

		if (n == keys.length) {
			resize(2 * keys.length);
		}

		for (int j = n; j > i; j--) {
			keys[j] = keys[j - 1];
			values[j] = values[j - 1];
		}
		keys[i] = key;
		values[i] = value;
		n++;

	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && keys[i].compareTo(key) == 0) {
			return values[i];
		} else {
			return null;
		}
	}

	public int size() {
		return n;
	}

	public void delete(Key key) {
		if (isEmpty())
			return;

		int i = rank(key);

		if (i == n || keys[i].compareTo(key) != 0) {
			// ключ не в таблице
			return;
		}

		for (int j = i; j < n - 1; j++) {
			keys[j] = keys[j + 1];
			values[j] = values[j + 1];
		}
		n--;
		keys[n] = null;
		values[n] = null;

		if (n > 0 && n == keys.length / 4) {
			resize(keys.length / 2);
		}
	}

	private void resize(int size) {
		Key[] tempKeys = (Key[]) new Comparable[size];
		Value[] tempValues = (Value[]) new Object[size];
		for (int i = 0; i < n; i++) {
			tempKeys[i] = keys[i];
			tempValues[i] = values[i];
		}
		keys = tempKeys;
		values = tempValues;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		OrderedSymbolTable<String, Integer> ost = new BinarySearch<String, Integer>(n);

	}
}
