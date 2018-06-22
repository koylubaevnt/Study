package com.koylubaevnt.algorithms.fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.koylubaevnt.algorithms.fundamentals.utils.ListIterator;

/**
 * {@code Stack} класс представляющий последний вошел - первый вышел (LIFO)
 * стек общих элементов.
 * 
 * Поддерживает обычные операции <em>push</em> и <em>pop</em>
 * наряду с операциями подглядывания на первый элемент <em>peek</em>,
 * пуста ли очередь <em>isEmpty</em> и итерацию через элементы очереди в LIFO порядке.
 * <p>
 * Эта реализация использует класс {@link Node} для связанного списока узлов. 
  *Операции <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em> и <em>isEmpty</em>
  *принимают постоянное время в худшем случае.
 * @author KojlubaevNT
 *
 * @param <Item>	Обобщенный тип элемента в стеке 
 */
public class Stack<Item> implements Iterable<Item> {
/*
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}
	*/
	private Node<Item> first;	//Верх стека
	private int n;				//Количество элемнтов в стеке
	
	public Stack() {
		first = null;
		n = 0;
	}
	
	/**
	 * Возвращает {@code true} если стек пуст
	 * 
	 * @return {@code true} если стек пуст; {@code false} в противном случае
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * Возвращает количество элементов в стеке
	 * 
	 * @return	Количество элементов в стеке
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Добавляет элемент в стек
	 * 
	 * @param item Элемент для добавления в стек
	 */
	public void push(Item item) {
		Node<Item> node = first;
		first = new Node<Item>();
		first.setItem(item);
		first.setNext(node);
		n++;
	}
	
	/**
	 * Удаляет и возвращает элемент из стека, который был добавлен последним
	 * 
	 * @throws	NoSuchElementException если стек пустой
	 * @return	Элемент из стека, который был добавлен последним
	 */
	public Item pop() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Item item = first.getItem();
		first = first.getNext();
		n--;
		return item;
	}
	
	/**
	 *	Возвращает элемент, который был добавлен последним
	 *
	 * @throws NoSuchElementException если стек пустой	
	 * @return Элемент, который был добавлен последним
	 */
	public Item peek() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.getItem();
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
		Stack<String> stack = new Stack<String>();
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNext()) {
			String item = scanner.next();
			if(!item.equals("-")) {
				stack.push(item);
			} else if (!stack.isEmpty()) {
				System.out.println(stack.pop() + " ");
			}
		}
		scanner.close();
		System.out.println("(" + stack.size() + " элементов осталось в стеке)");
		for(String item : stack) {
			System.out.print(item + " ");
		}
	}

}
