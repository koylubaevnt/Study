package com.koylubaevnt.own.others;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArrayStack<E> implements Stack<E> {

	private E[] elements;
	private int capacity;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		size = 0;
		elements = (E[]) new Object[capacity];
	}
	
	@Override
	public void push(E element) throws StackException {
		if(isFull()) {
			throw new StackException("Stack overflow");
		}
		elements[size] = element;
		size++;
	}

	@Override
	public E pop() throws StackException {
		if(isEmpty()) {
			throw new StackException("Stack is empty");
		}
		E obj = elements[size];
		elements[size] = null;
		size--;
		return obj;
	}

	@Override
	public E peek() {
		return elements[size];
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == capacity;
	}

	@Override
	public void pushAll(Collection<? extends E> src) throws StackException {
		for(E element : src) {
			push(element);
		}

	}

	@Override
	public void popAll(Collection<? super E> dst) throws StackException {
		for(int i = 0; i < size; i++) {
			dst.add(elements[i]);
		}

	}

	public static void main(String[] args) throws StackException {
		ArrayStack<Integer> ints = new ArrayStack<>(10);
		ints.push(10);
		ints.push(12);
		
		List<Number> nums = new ArrayList<>();
		// Stack -> Collection
		ints.popAll(nums);
		nums.stream().forEach(System.out::println);
		System.out.println("===========================");
		ArrayStack<Number> nums2 = new ArrayStack<>(10);
		List<Integer> ints2 = new ArrayList<>();
		ints2.add(9);
		ints2.add(8);
		// Collection -> Stack
		nums2.pushAll(ints2);
		ints2.stream().forEach(System.out::println);
		
	}
	
}
