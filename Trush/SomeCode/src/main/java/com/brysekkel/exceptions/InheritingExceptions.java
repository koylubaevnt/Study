package com.brysekkel.exceptions;

class SimpleException extends Exception {}

public class InheritingExceptions {

	public void f() throws SimpleException {
		System.out.println("Возбуждаем SimpleException из f()");
		throw new SimpleException();
	}
	
	public static void main(String[] args) {
		InheritingExceptions sed = new InheritingExceptions();
		try {
			sed.f();
		} catch (SimpleException e) {
			System.out.println("Перехвачено");
		}

	}

}
