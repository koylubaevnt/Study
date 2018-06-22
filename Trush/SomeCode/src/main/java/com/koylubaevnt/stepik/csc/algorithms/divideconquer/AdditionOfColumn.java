package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

public class AdditionOfColumn {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(multiply(23, 20));
		System.out.println(multiply(1, 20));
	}

	
	/**
	 * Сложение в столбик
	 * 
	 * перенос:	1			1	1	1
	 * 				1	1	0	1	0	1	(53)
	 * 				1	0	0	0	1	1	(35)
	 * 			1	0	1	1	0	0	0	(88)
	 * 
	 * Умножение в столбик
	 *
	 * 						1	1	0	1	(13)
	 * 						1	0	1	1	(11)
	 * 						1	1	0	1	(1101 * 1)
	 * 					1	1	0	1		(1101 * 1, сдвинутое на 1)
	 * 				0	0	0	0			(1101 * 0, сдвинутое на 2)
	 * 			1	1	0	1				(1101 * 1, сдвинутое на 3)
	 * 		1	0	0	0	1	1	1	1	(143)
	 * 
	 * Время работы: O (n^2)
	 */
	
	/**
	 * Рекурентная формула
	 * 
	 * 		2	* {y \2}		-> если y четно ({} - округление вниз)
	 * y = 
	 * 		1 + 2 * {y\2}		-> если y нечетно
	 * 
	 * 			2	* (x * {y \2})		-> если y четно
	 * x * y = 
	 * 			1 + 2 * (x * {y\2})		-> если y нечетно
	 */
	
	/**
	 * Вход: два n-битовых целых числа (x >= 0, y >= 0)
	 * Выход: x * y
	 * 
	 * Время работы: O(n^2)
	 */
	private static int multiply(int x, int y) {
		if(y == 0) {
			return 0;
		}
		int z = multiply(x, y / 2);
		if (y % 2 == 0) {
			return 2 * z;
		} else {
			return x + 2 * z; 
		}
	}
	
	
}
