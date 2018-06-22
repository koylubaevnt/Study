package com.koylubaevnt.stepik.csc.algorithms.common;

import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {

	
	
	/**
	 * Реализация расчета числа "Фибоначчи" на основе рекурсии.
	 * 
	 * @param n	- число 
	 * @return n-е число Фибоначчи
	 */
	public static long recursiveFibonacci(int n) {
		if(n > 40) {
			return -1;
		}
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
		}
	}

	/**
	 * Реализация расчета числа "Фибоначчи" на основе массива.
	 * 
	 * @param n
	 * @return n-е число Фибоначчи
	 */
	public static long arrayFibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			long[] array = new long[n + 1];
			array[0] = 0;
			array[1] = 1;
			for (int i = 2; i < n + 1; i++) {
				array[i] = array[i - 1] + array[i - 2];
			}
			return array[n];
		}
	}

	/**
	 * Реализация расчета числа "Фибоначчи" на основе запоминания последних двух чисел
	 * 
	 * @param n
	 * @return n-е число Фибоначчи
	 */
	public static long variableFibonacci(int n) {
		if (n <= 1) {
			return n;
		} else {
			long previous = 0;
			long current = 1;
			long tmp;
			for (int i = 2; i < n + 1; i++) {
				tmp = previous + current;
				previous = current;
				current = tmp;
			}
			return current;
		}
	}

	/**
	 * Реализация расчета числа "Фибоначчи" на основе матрицы (иммитация матрицы через переменные)
	 * 
	 * @param n
	 * @return n-е число Фибоначчи
	 */
	public static long matrixFibonacci(int n) {
		long a = 1, b = 1, c = 1, d = 0, rc = 0, rd = 1, ta, tb, tc;
		/*
		 * Матрица:
		 * 	a	b
		 * 	с	d
		 * 
		 * Вектор
		 * 	rc	rd
		 * 
		 * */

		while (n != 0) {
			if (n % 2 != 0) {
				// Если степень нечетная
				// Умножаем вектор R на матрицу A
				tc = rc;
				rc = rc * a + rd * c;
				rd = tc * b + rd * d;
			}

			// Умножаем матрицу A на саму себя
			ta = a;
			tb = b;
			tc = c;
			a = a * a + b * c;
			b = ta * b + b * d;
			c = c * ta + d * c;
			d = tc * tb + d * d;
			
			n >>= 1; // Уменьшаем степень вдвое

		}
		return rc;
	}
	
	/**
	 * Реализация расчета числа "Фибоначчи" на основе матрицы (реальные матрицы и массивы)
	 * 
	 * @param n
	 * @return n-е число Фибоначчи
	 */
	public static long matrix2Fibonacci(int n) {
		int rows = 2;
		
		long[][] result = new long[rows][rows];
		long[][] matrix = {{1, 1}, {1, 0}};
		long[][] matrixCopy = new long[rows][rows];
		
		long[] array = {0, 1};
		long[] arrayCopy = new long[rows];
		
		while (n != 0) {
			if (n % 2 != 0) {
				// Если степень нечетная
				// Умножаем вектор R на матрицу A
				for (int i = 0; i < rows; i++) {
					for(int j = 0; j < rows; j++) {
						arrayCopy[i] += array[j] * matrix[i][j];
					}
				}
				array = Arrays.copyOf(arrayCopy, arrayCopy.length);
				Arrays.fill(arrayCopy, 0);
			}

			//Копируем матрицу для умножения саму на себя
			for(int i = 0; i < matrix.length; i++) {
				matrixCopy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
			}
			
			// Умножаем матрицу A на саму себя
			for (int i = 0; i < rows; i++) {
				for(int k = 0; k < rows; k++) {
					for(int j = 0; j < rows; j++) {
						result[i][k] += matrix[i][j] * matrixCopy[j][k];
					}
				}
			}
			//Копируем ссылку на новую матрицу
			for(int i = 0; i < matrix.length; i++) {
				matrix[i] = Arrays.copyOf(result[i], result[i].length);
				Arrays.fill(result[i], 0);
			}
			
			n >>= 1; // Уменьшаем степень вдвое

		}
		return array[0];
	}

	
	/* последняя цифра большого числа Фибоначчи (остаток от деления n-го числа Фибоначчи на m): 
	 * если 0 ≤a,b ≤9 — последние цифры чисел Fi и Fi+1 соответственно, 
	 * то (a+b) mod 10 — последняя цифра числа Fi+2.
	*/
	public static long recursiveFibonacci(int n, int m) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			return (recursiveFibonacci(n - 1, m) + recursiveFibonacci(n - 2, m)) % m;
		}
	}

	public static long arrayFibonacci(int n, int m) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return 1;
		} else {
			long[] array = new long[n + 1];
			array[0] = 0;
			array[1] = 1;
			for (int i = 2; i < n + 1; i++) {
				array[i] = (array[i - 1] + array[i - 2]) % m;
			}
			return array[n];
		}
	}

	public static long variableFibonacci(int n, int m) {
		if (n <= 1) {
			return n;
		} else {
			long previous = 0;
			long next = 1;
			long tmp;
			for (int i = 2; i < n + 1; i++) {
				tmp = (previous + next) % m;
				previous = next;
				next = tmp;
			}
			return next;
		}
	}

	public static long matrixFibonacci(long n, long m) {
		long a = 1, ta, b = 1, tb, c = 1, rc = 0, tc, d = 0, rd = 1;

		while (n != 0) {
			if (n % 2 != 0) {
				// Если степень нечетная
				// Умножаем вектор R на матрицу A
				tc = rc;
				rc = (rc * a + rd * c) % m;
				rd = (tc * b + rd * d) % m;
			}

			// Умножаем матрицу A на саму себя
			ta = a;
			tb = b;
			tc = c;
			a = (a * a + b * c) % m;
			b = (ta * b + b * d) % m;
			c = (c * ta + d * c) % m;
			d = (tc * tb + d * d) % m;

			n >>= 1; // Уменьшаем степень вдвое

		}
		return rc;
	}
	
	private static int ALGORITHMS_COUNT = 5;

	/**
	 * Функция для тестирования чисел Фибоначчи
	 *  
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		
		long[] fib = new long[ALGORITHMS_COUNT];
		long[] timeSpent = new long[ALGORITHMS_COUNT];
		for (int j = 0; j <= n; j++) {
			long startTime = System.currentTimeMillis();
			fib[0] = recursiveFibonacci(j);
			timeSpent[0] = System.currentTimeMillis() - startTime;
			startTime = System.currentTimeMillis();
			fib[1] = arrayFibonacci(j);
			timeSpent[1] = System.currentTimeMillis() - startTime;
			startTime = System.currentTimeMillis();
			fib[2] = variableFibonacci(j);
			timeSpent[2] = System.currentTimeMillis() - startTime;
			startTime = System.currentTimeMillis();
			fib[3] = matrixFibonacci(j);
			timeSpent[3] = System.currentTimeMillis() - startTime;
			startTime = System.currentTimeMillis();
			fib[4] = matrix2Fibonacci(j);
			timeSpent[4] = System.currentTimeMillis() - startTime;
			
			System.out.println("N: " + j);
			for (int i = 0; i < ALGORITHMS_COUNT; i++) {
				System.out.println("Algorithm: " + i + ". Fibonacci: " + fib[i] + ". Spent time: " + timeSpent[i]);
			}
		}
		
	}
}
