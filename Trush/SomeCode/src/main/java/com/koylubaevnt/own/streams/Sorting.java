package com.koylubaevnt.own.streams;

import java.util.Arrays;

public class Sorting {

	private static long countExchange = 0;
	private static long countIteration = 0;
	
	public static void main(String[] args) {
		Integer[] array = {5, 2, 1, 3, 9, 0, 4, 6, 8, 7};
		Integer[] result = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		Sorting sorting = new Sorting();
		Integer[] copy = Arrays.copyOf(array, array.length);
		
		System.out.println(Arrays.toString(array));
		System.out.print("[");
		String space = " ";
		for(int i = 0; i < array.length; i++) {
			if(i >= array.length - 1) {
				space = "";
			}
			System.out.print(i + space);
			
		}
		System.out.println("]");
		System.out.println("Start sorting");
		countExchange = 0;
		countIteration = 0;
		//sorting.stupidSorting(copy);
		//sorting.bubbleSorting(copy);
		sorting.shakeSorting(copy);
		System.out.println("Stop sorting");
		System.out.println(countIteration);
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(copy));
		
	}
	
	
	public void stupidSorting(Comparable[] a) {
		System.out.println("Глупая сортировка.");
		int n = a.length;
		int i = 0;
		while(true) {
			if(i >= n - 1) {
				break;
			}
			int j = i + 1;
			if(a[i].compareTo(a[j]) > 0) {
				exchange(a, i, j);
				System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
				i = 0;
				continue;
			}
			i++;
			countIteration++;
		}
		
	}
	
	
	public void bubbleSorting(Comparable[] a) {
		System.out.println("Пузырьковая сортировка.");
		int n = a.length;
		boolean isSorted = false;
		int l = 1;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i < n - l; i++) { 
				int j = i + 1;
				if(a[i].compareTo(a[j]) > 0) {
					exchange(a, i, j);
					System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
					isSorted = false;
				}
				countIteration++;
			}
			l++;
		}
		
		
	}
	
	public void shakeSorting(Comparable[] a) {
		System.out.println("Шейкерная сортировка");
		int n = a.length;
		boolean isSorted = false;
		int l = 1;
		int r = 0;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i < n - l; i++) { 
				int j = i + 1;
				if(a[i].compareTo(a[j]) > 0) {
					exchange(a, i, j);
					System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
					isSorted = false;
				}
				countIteration++;
			}
			for(int i = n - l; i > r; i--) { 
				int j = i - 1;
				if(a[i].compareTo(a[j]) < 0) {
					exchange(a, i, j);
					System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
					isSorted = false;
				}
				countIteration++;
			}
			l++;
			r++;
		}
	}
	
	public void evenOddSorting(Comparable[] a) {
		System.out.println("Чет-нечет сортировка");
		int n = a.length;
		boolean isSorted = false;
		int l = 1;
		int r = 0;
		while(!isSorted) {
			isSorted = true;
			for(int i = 0; i < n - l; i++) { 
				int j = i + 1;
				if(a[i].compareTo(a[j]) > 0) {
					exchange(a, i, j);
					System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
					isSorted = false;
				}
				countIteration++;
			}
			for(int i = n - l; i > r; i--) { 
				int j = i - 1;
				if(a[i].compareTo(a[j]) < 0) {
					exchange(a, i, j);
					System.out.println(countExchange + ": " + Arrays.toString(a) + ", индекс: " + i + "<->" + j + ", значение: "+ a[i] + "<->" + a[j]);
					isSorted = false;
				}
				countIteration++;
			}
			l++;
			r++;
		}
	}
	
	private void exchange(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
		countExchange++;
	}
}
