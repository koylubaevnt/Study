package com.brysekkel.generics;

import java.util.LinkedList;

public class Stack<T> {

	private LinkedList<T> storage = new LinkedList<>();
	
	public void push(T e) {
		storage.addFirst(e);
	}
	
	public T peek() {
		return storage.getFirst();
	}
	
	public T pop() {
		return storage.removeFirst();
	}
	
	public boolean empty() {
		return storage.isEmpty();
	}
	
	@Override
	public String toString() {
		return storage.toString();
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		for(String s : "My dog has fleas".split(" "))
			stack.push(s);
		while(!stack.empty()) 
			System.out.print(stack.pop() + " ");
			
	}

}
