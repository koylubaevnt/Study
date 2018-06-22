package com.koylubaevnt.stepik.csc.algorithms.dynamicprogramming;

import java.io.IOException;
import java.util.Scanner;

public class Fibonacchi {

	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		new Fibonacchi().run();
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + " ms");
	}
	
	private void run() throws IOException {
		String uri = "generate-input.txt";
		//BufferedReader  input = new BufferedReader(new FileReader(uri));
		//String token = input.readLine();
		//int n = Integer.parseInt(token);
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		f = new long[n + 1];
		//Arrays.fill(f, -1);
		//long fib = fibonacchiTD(n);
		//long fib = fibonacchiBU(n);
		long fib = fibonacchiBUImproved(n);
		System.out.println(fib);
		//input.close();
		scanner.close();
	}

	private long[] f;
	
	/**
	 * Числа Фибоначи
	 * 
	 * Динамическое программирование назад
	 * 
	 * Время работы: O (n^2)
	 * @param n
	 * @return
	 */
	public long fibonacchiTD(int n) {
		if(f[n] == -1) {
			if(n <= 1) {
				f[n] = n;
			} else {
				f[n] = fibonacchiTD(n - 1) + fibonacchiTD(n - 2);
			}
		}
		return f[n];
	}

	/**
	 * Числа Фибоначи
	 * 
	 * Динамическое программирование вперед
	 * 
	 * Время работы: O (n^2)
	 * @param n
	 * @return
	 */
	public long fibonacchiBU(int n) {
		f[0] = 0;
		f[1] = 1;
		for(int i = 2; i <= n; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}


	/**
	 * Числа Фибоначи
	 * 
	 * Динамическое программирование вперед: Уменьшение памяти
	 * 
	 * Время работы: O (n^2)
	 * @param n
	 * @return
	 */
	public long fibonacchiBUImproved(int n) {
		if (n <= 1) return n;
		
		long prev = 0;
		long curr = 1;
		long next;
		for(int i = 2; i <= n; i++) {
			next = prev + curr;
			prev = curr;
			curr = next;
		}
		return curr;
	}

}
