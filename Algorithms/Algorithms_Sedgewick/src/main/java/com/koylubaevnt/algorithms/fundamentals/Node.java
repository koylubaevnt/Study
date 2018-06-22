package com.koylubaevnt.algorithms.fundamentals;

/**
 * Класс {@code Node} описывает узел, который содержит:
 * <ul>
 * <li>{@code item} - Значение обобщенного типа.</li>
 * <li>{@code next} - Ссылку на следующий узел</li>
 * </ul>
 * 
 * @author KojlubaevNT
 *
 * @param <Item>	Обобщенный тип, который хранится в элементе узла
 */
public class Node<Item> {
	private Item item;
	private Node<Item> next;
	
	public Node() {
		this.next = null;
		this.item = null;
	}
	
	public Node(Node<Item> node, Item item) {
		this.next = node;
		this.item = item;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Node<Item> getNext() {
		return next;
	}

	public void setNext(Node<Item> node) {
		this.next = node;
	}

	@Override
	public String toString() {
		return item.toString();
	}
	
	
}
