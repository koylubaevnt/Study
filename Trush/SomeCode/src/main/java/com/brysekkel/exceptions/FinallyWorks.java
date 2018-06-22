package com.brysekkel.exceptions;

class ThreeEception extends Exception {}

public class FinallyWorks {

	static int count = 0;
	
	public static void main(String[] args) {
		while(true) {
		try{
			if(count++ == 0)
				throw new ThreeEception();
			System.out.println("нет исключения");
		} catch (ThreeEception e) {
			System.out.println("ThreeEception");
		} finally {
			System.out.println("В блоке finally");
			if(count == 2) break;
		}
		}

	}

}
