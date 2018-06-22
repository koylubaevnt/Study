package com.brysekkel.generics;

class ThreeException extends Exception {}

public class FinallyWorks {

	static int count = 0;
	
	public static void main(String[] args) {
		while (true) {
			try {
				if(count++ == 0)
					throw new ThreeException();
				System.out.println("Нет исключения");
			} catch (ThreeException e) {
				System.out.println("Исключение ThreeException");
			} finally {
				System.out.println("В блоке finally");
				if(count == 2) break; //Выход из цикла
			}
		}

	}

}
