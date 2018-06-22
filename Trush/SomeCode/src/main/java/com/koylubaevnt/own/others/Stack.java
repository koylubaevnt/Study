package com.koylubaevnt.own.others;

import java.util.Collection;

public interface Stack<E> {

	void push(E element) throws StackException;
	
	E pop() throws StackException;
	
	E peek();
	
	int getSize();
	
	boolean isEmpty();
	
	boolean isFull();
	
	//Немного не правильно. Как правильно исправить	
	//void pushAll(Collection<E> src) throws StackException;
	void pushAll(Collection<? extends E> src) throws StackException;
	
	//Немного не правильно. Как правильно исправить	
	//void popAll(Collection<E> dst) throws StackException;
	void popAll(Collection<? super E> dst) throws StackException;
}
