package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sorting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {5, 3, 4, 3, 6, 2, 1};
		int[] a2 = {1, 1, 2, 3, 4, 5, 5};
		int[] a3 = {7, 2, 5, 3, 7, 13, 1, 6};
		System.out.println("Original data");
		System.out.println(Arrays.toString(a));
		System.out.println(Arrays.toString(a2));
		System.out.println(Arrays.toString(a3));
		
		System.out.println("Insertion sort");
		int[] copy = Arrays.copyOf(a, a.length);
		insertionSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		insertionSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		insertionSort(copy);
		System.out.println(Arrays.toString(copy));
		
		System.out.println("Merge sort");
		copy = Arrays.copyOf(a, a.length);
		mergeSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		mergeSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		mergeSort(copy);
		System.out.println(Arrays.toString(copy));
		
		System.out.println("Iteretive Merge sort");
		copy = Arrays.copyOf(a, a.length);
		System.out.println(Arrays.toString(iterativeMergeSort(copy)));
		copy = Arrays.copyOf(a2, a2.length);
		System.out.println(Arrays.toString(iterativeMergeSort(copy)));
		copy = Arrays.copyOf(a3, a3.length);
		System.out.println(Arrays.toString(iterativeMergeSort(copy)));
		
		System.out.println("Quick sort");
		copy = Arrays.copyOf(a, a.length);
		quickSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		quickSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		quickSort(copy);
		System.out.println(Arrays.toString(copy));

		System.out.println("Quick3 sort");
		copy = Arrays.copyOf(a, a.length);
		quick3Sort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		quick3Sort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		quick3Sort(copy);
		System.out.println(Arrays.toString(copy));
		
		System.out.println("Selection sort");
		copy = Arrays.copyOf(a, a.length);
		selectionSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		selectionSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		selectionSort(copy);
		System.out.println(Arrays.toString(copy));
		
		System.out.println("Heap sort");
		copy = Arrays.copyOf(a, a.length);
		heapSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a2, a2.length);
		heapSort(copy);
		System.out.println(Arrays.toString(copy));
		copy = Arrays.copyOf(a3, a3.length);
		heapSort(copy);
		System.out.println(Arrays.toString(copy));
		
		
		System.out.println("Count sort");
		copy = new int[] {2, 1, 1, 1, 3, 2, 2, 2, 3, 2, 3, 2, 2, 1, 1};
		System.out.println(Arrays.toString(countSorting(copy, 4)));
		copy = new int[] {2, 3, 9, 2, 9};
		System.out.println(Arrays.toString(countSorting(copy, 10)));
				
		
	}

	/**
	 * Сортировка вставками
	 * 
	 * Сортировка на "месте", не исопльзует память
	 * 
	 * Время работы: O-т(n^2) (не быстрее и не медленнее О-тетта)
	 * @param a
	 */
	private static void insertionSort(int[] a) {
		for(int i = 1; i < a.length; i++) {
			for(int j = i; j > 0; j--) {
				if(a[j] < a[j - 1]) {
					int t = a[j];
					a[j] = a[j - 1];
					a[j - 1] = t;
				}
			}
		}
	}
	
	
	/**
	 * Сортировка слиянием
	 * 
	 * Время работы: O(n)
	 * @param a
	 */
	private static int[] copy;
	
	private static void mergeSort(int[] a) {
		copy = new int[a.length];
		mergeSort(a, 0, a.length - 1);
	}
	
	private static void mergeSort(int[] a, int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			mergeSort(a, l, m);			//Берем первую половину массива, для сортировки
			mergeSort(a, m + 1, r);		//Берем вторую половину массива, для сортировки
			merge(a, l, m, r);			//Объединяем два массива в один (в порядке упорядочивания)
		}
	}
	
	private static void merge(int[] a, int l, int m, int r) {
		int i = l;
		int j = m + 1;
		//Копируем массив a в copy (чтобы потом сравнивая 2 элемента в массиве array выбрать меньший)
		for(int k = l; k <= r; k++) {
			copy[k] = a[k];
		}
		
		for(int k = l; k <= r; k++)
			if(i > m) {
				//Первая половина закончилась, используем вторую
				a[k] = copy[j];
				j++;				
			} else if (j > r) {
				//Вторая половина закончилась, используем первую
				a[k] = copy[i];
				i++;
			} else if (copy[i] < copy[j]) {
				//Элемент в первом масиве меньше, чем во втором
				a[k] = copy[i];
				i++;
			} else {
				//Элемент во втором масиве меньше, чем в первом
				a[k] = copy[j];
				j++;
		}
	}
	
	
	/**
	 * Сортировка слиянием
	 * 
	 * Итеративная реализация
	 * 
	 * Время работы: O(n * log n)
	 * @param a
	 */
	private static Integer[] iterativeMergeSort(int[] a) {
		Queue<Integer[]> queue = new LinkedList<>();
		for(int i = 0; i < a.length; i++) {
			queue.add(new Integer[] {a[i]});
		}
		
		Integer[] copy1;
		while(queue.size() > 1) {
			copy1 = merge(queue.remove(), queue.remove());
			queue.add(copy1);
			
		}
		return queue.remove();
	}
	
	private static Integer[] merge(Integer[] a, Integer[] b) {
		Integer[] cp = new Integer[a.length + b.length];
		int i = 0;
		int j = 0;
		for(int k = 0; k <= a.length + b.length - 1; k++)
			if(i >= a.length) {
				//Первая массив закончился, используем второй
				cp[k] = b[j];
				j++;				
			} else if (j >= b.length) {
				//Вторая половина закончилась, используем первую
				cp[k] = a[i];
				i++;
			} else if (a[i] < b[j]) {
				//Элемент в первом масиве меньше, чем во втором
				cp[k] = a[i];
				i++;
			} else {
				//Элемент во втором масиве меньше, чем в первом
				cp[k] = b[j];
				j++;
		}
		return cp;
	}
	
	
	private static void quickSort(int[] a) {
		quickSort(a, 0, a.length - 1);
	}
	
	//ВНИМАНИЕ - выбираем первый элемент!!! Если массив не перемешан, то может быть худший случай
	private static void quickSort(int[] a, int l, int r) {
		/*
		if(l >= r) {
			return;
		}
		int m = partition(a, l, r);
		quickSort(a, l, m - 1);
		quickSort(a, m + 1, r);
		*/
		//Элиминация хвостовой рекурсии
		while (l < r) {
			int m = partition(a, l, r);
			if(m - l >= m - r) {//Выбираем наименьший путь
				quickSort(a, l, m - 1);
				l = m + 1;
			} else {
				quickSort(a, m + 1, r);
				l = m - 1;
			}
		}
		//quickSort(a, m + 1, r);
	}
	
	private static int partition(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for(int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				int t = a[j];
				a[j] = a[i];
				a[i] = t;
			}
		}
		int t = a[l];
		a[l] = a[j];
		a[j] = t;
		return j;
	}
	

	private static void quick3Sort(int[] a) {
		quick3Sort(a, 0, a.length - 1);
	}
	
	//ВНИМАНИЕ - выбираем первый элемент!!! Если массив не перемешан, то может быть худший случай
	private static void quick3Sort(int[] a, int l, int r) {
		if(l >= r) {
			return;
		}
		int lt = l;
		int gt = r;
		int x = a[l];
		int i = l;
		
		while(i <= gt) {
			int cmp = a[i] - x; 
			if(cmp < 0) {
				int t = a[lt];
				a[lt] = a[i];
				a[i] = t;
				lt++;
				i++;
			} else if(cmp > 0) {
				int t = a[gt];
				a[gt] = a[i];
				a[i] = t;
				gt--;
			} else {
				i++;
			}
		}
		
		quick3Sort(a, l, lt - 1);
		quick3Sort(a, gt + 1, r);
		
	}
	
	/**
	 * Сортировка выбором
	 * 
	 * Время работы: O(n^2)
	 */
	
	private static void selectionSort(int[] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			int k = i;
			for(int j = i + 1; j < n; j++) {
				if(a[j] < a[k]) {
					k = j;
				}
			}
			int t = a[i];
			a[i] = a[k];
			a[k] = t;
		}
	}
	
	
	/**
	 * Сортировка кучей
	 * 
	 * Время работы: O(n * log n)
	 */
	private static void heapSort(int[] a) {
		Queue<Integer> queue = new PriorityQueue<>();
		int n = a.length;
		for(int i = 0; i < n; i++) {
			queue.add(a[i]);
		}
		for(int i = 0; i < n; i++) {
			a[i] = queue.remove();
		}
	}
	
	private static void heap2Sort(int[] a) {
		
		buildMaxHeap(a);
		int n = a.length;
		int size = n;
		for(int i = n; i > 1; i--) {
			int t = a[size];
			a[size] = a[0];
			a[0] = t;
			size--;
			shiftDown(a, 1);
		}
	}
	
	private static void buildMaxHeap(int[] a) {
		for(int i = a.length / 2; i > 0; i--) {
			shiftDown(a, i);
		}
	}
	
	private static void shiftDown(int[] a, int step) {
		
	}
	
	/**
	 * Сортировка подсчетом
	 * 
	 * Массив содержит целые числа от 1...M
	 * @param a
	 */
	public static int[] countSorting(int a[], int max) {
		int n = a.length;
		int[] b = new int[max];
		int[] c = new int[n];
		
		for(int i = 0; i < n; i++) {
			b[a[i] - 1] = b[a[i] - 1] + 1;
		}
		
		for(int i = 1; i < max; i++) {
			b[i] = b[i] + b[i - 1];
		}
		
		for(int i = n - 1; i >= 0; i--) {
			b[a[i] - 1] = b[a[i] - 1] - 1;
			c[b[a[i] - 1]] = a[i];
		}
		return c;
	}
}
