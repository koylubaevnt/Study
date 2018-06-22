package com.brysekkel.exceptions;

class MyException extends Exception {
	
	public MyException() { super(); }
	public MyException(String msg) { super(msg); }
	
}

public class FullConstructors {

	public static void f() throws MyException {
		System.out.println("Возбуждаем MyException из f()");
		throw new MyException();
	}
	
	public static void g() throws MyException {
		System.out.println("Возбуждаем MyException из g()");
		throw new MyException("Создано в g()");
	}
	
	public static void main(String[] args) {
		try {
			f();
		} catch (MyException e) {
			e.printStackTrace(System.out);
		}
		try {
			g();
		} catch (MyException e) {
			//System.out.println("Перехвачено");
			e.printStackTrace(System.out);
		}
	}

}
