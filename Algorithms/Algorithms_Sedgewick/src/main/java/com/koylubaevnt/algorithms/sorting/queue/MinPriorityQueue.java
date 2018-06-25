package com.koylubaevnt.algorithms.sorting.queue;

/**
 * Очередь с приоритетом (Минимальный элемент на вершине)
 * 
 * @author KojlubaevNT
 *
 * @param <Key> Тип ключ
 */
public class MinPriorityQueue<Key extends Comparable<Key>> {

	private int n = 0;		//Количество узлов
	private Key[] array;	//Пирамидально упорядоченное дполное бинарное дерево
	
	/**
	 * Создание очереди с приоритетами с первоначальным объемом
	 * 
	 * @param max первоначальный объем
	 */
	public MinPriorityQueue(int max) {
		array = (Key[]) new Comparable[max + 1];
		n = 0; 
	}
	
	/**
	 * Создание очереди с приоритетами из ключей в массиве
	 * 
	 * @param a массив ключей
	 */
	public MinPriorityQueue(Key[] a) {
		array = a;
	}
	
	/**
	 * Вставка ключа в очередь с приоритетами
	 * 
	 * @param v Ключ
	 */
	public void insert(Key v) {
		array[++n] = v;
		swim(n);
	}
	
	/**
	 * Возврат наименьшего ключа
	 * 
	 * @return Наибольший ключ в очереди с приоритетами
	 */
	public Key min() {
		return array[1];
	}
	
	/**
	 * Возврат и извлечение наименьшего ключа
	 * 
	 * @return Наименьший ключ в очереди с приоритетами
	 */
	public Key delete() {
		Key value = array[1];
		exchange(1, n--);
		array[n+1] = null;
		sink(1);
		return value;
	}
	
	/**
	 * Пуста ли очередь с приоритетами
	 * @return Пустая ли очередь
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Количество ключей в очереди с приоритетами
	 * 
	 * @return Количество ключей
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Сравнение значений по индексам 
	 * 
	 * @param i индекс первого элемента
	 * @param j индекс второго элемента
	 * @return <code>true</code>, если первый элемент больше второго
	 */
	private boolean greather(int i, int j) {
		return array[i].compareTo(array[j]) > 0;
	}
	
	/**
	 * Обмен значиний по индексам
	 * @param i индекс первого элемента для обмена
	 * @param j индекс второго элемента для обмена
	 */
	private void exchange(int i, int j) {
		Key t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	
	/**
	 * Воссходящее восстановление пирамидальности (всплытие)
	 * 
	 * @param k - узел нарущающий пирамидальный порядок (ключ меньше, чем у родителя)
	 */
	private void swim(int k) {
		while(k > 1 && greather(k / 2, k)) {
			exchange(k / 2, k);
			k = k / 2;
		}
	}
	
	/**
	 * Ниссходящее восстановление пирамидальности (погружение)
	 * 
	 * @param k - узел нарущающий пирамидальный порядок (ключ больше, чем у потомка)
	 */
	private void sink(int k) {
		while(2 * k <= n) {
			int j = 2 * k;
			if(j < n && greather(j, j+1)) {
				j++;
			}
			if(!greather(k, j)) {
				break;
			}
			exchange(k, j);
			k = j;
		}
	}
	
	public void show() {
		for(int i = 1; i <= n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		MinPriorityQueue<String> pq = new MinPriorityQueue<String>(10);
		pq.show();
		pq.insert("P");
		pq.show();
		pq.insert("Q");
		pq.show();
		pq.insert("E");
		pq.show();
		System.out.println(pq.delete());
		pq.show();
		pq.insert("X");
		pq.show();
		pq.insert("A");
		pq.show();
		pq.insert("M");
		pq.show();
		System.out.println(pq.delete());
		pq.show();
		pq.insert("P");
		pq.show();
		pq.insert("L");
		pq.show();
		pq.insert("E");
		pq.show();
		System.out.println(pq.delete());
		pq.show();
	}
}