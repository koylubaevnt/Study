package com.koylubaevnt.algorithms.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.koylubaevnt.algorithms.fundamentals.utils.ListIterator;

/**
 * {@code Queue} класс представляющий первый вошел - первый вышел (FIFO)
 * очередь  общих элементов.
 * 
 * Поддерживает обычные операции <em>enqueue</em> и <em>dequeue</em>
 * наряду с операциями подглядывания на первый элемент <em>peek</em>,
 * пуста ли очередь <em>isEmpty</em> и итерацию через элементы очереди в FIFO порядке.
 * <p>
 * Эта реализация использует класс {@link Node} для связанного списока узлов. 
  *Операции <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em> и <em>isEmpty</em>
  *принимают постоянное время в худшем случае.
  *
 * @author KojlubaevNT
 *
 * @param <Item> Обобщенный тип элемента в очереди 
 */
public class Queue<Item> implements Iterable<Item> {
	/*
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	*/
	private Node<Item> first;	//Начало очереди
	private Node<Item> last;	//Конец очереди
	private int n;				//Количество элементов в очереди

	public Queue() {
		first = null;
		last = null;
		n = 0;
	}
	
	/**
	 * Возвращает {@code true} если очередь пуста
	 * 
	 * @return {@code true} если очередь пуста; {@code false} в противном случае
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Возвращает количество элементов в очереди
	 * 
	 * @return	Количество элементов в очереди
	 */
	public int size() {
		return n;
	}
	
	/**
	 *	Возвращает элемент, который был добавлен первым
	 *
	 * @throws NoSuchElementException если очередь пуста	
	 * @return Элемент, который был добавлен первым
	 */
	public Item peek() {
		if(isEmpty()) throw new NoSuchElementException("Очередь пуста");
		return first.getItem();
	}
	
	/**
	 * Добавляет элемент в очередь
	 * 
	 * @param item	Элемент для добавления в очередь
	 */
	public void enqueue(Item item) {
		Node<Item> node = last;
		last = new Node<Item>();
		last.setItem(item);
		last.setNext(null);
		if (isEmpty()) {
			first = last;
		} else {
			node.setNext(last);
		}
		n++;
	}
	
	/**
	 * Удаляет и возвращает элемент из очереди, который был добавлен первым
	 * 
	 * @throws	NoSuchElementException если очередь пуста
	 * @return	Элемент из очереди, который был добавлен первым
	 */
	public Item dequeue() {
		if(isEmpty()) throw new NoSuchElementException("Очередь пуста");
		Item item = first.getItem();
		first = first.getNext();
		n--;
		if(isEmpty()) {
			last = null;
		}
		return item;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Item item : this) {
			sb.append(item + " ");
		}
		return sb.toString();
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			String item = scanner.next();
			if(!item.equals("-")) {
				queue.enqueue(item);
			} else if (!queue.isEmpty()) {
				System.out.println(queue.dequeue() + " ");
			}
		}
		scanner.close();
		System.out.println("(" + queue.size() + " элементов осталось в очереди)");
		for(String item : queue) {
			System.out.print(item + " ");
		}

	}

}
