package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Задача на программирование: точки и отрезки
 * 
 * В первой строке задано два целых числа 1 ≤ n ≤ 50000 и 1 ≤ m ≤ 50000 —
 * количество отрезков и точек на прямой, соответственно. Следующие n строк
 * содержат по два целых числа ai и bi (ai ≤ bi) — координаты концов отрезков.
 * Последняя строка содержит m целых чисел — координаты точек. Все координаты не
 * превышают 10^8 по модулю. Точка считается принадлежащей отрезку, если она
 * находится внутри него или на границе. Для каждой точки в порядке появления во
 * вводе выведите, скольким отрезкам она принадлежит.
 * 
 * Sample Input: 2 3 0 5 7 10 1 6 11
 * 
 * Sample Output: 1 0 0
 * 
 * Sample Input: 5 6 2 3 1 4 1 3 3 5 3 4 2 1 4 5 6 0
 * 
 * Sample Output: 3 2 3 1 0 0
 * 
 * @author KojlubaevNT
 *
 */
public class PointsAndLines {

	public static void main(String[] args) throws IOException {
		int q = 10;
		int[] qq = {1,2,3,3,3,3,3,3,5,6};
		PointsAndLines p = new PointsAndLines();
		System.out.println(p.binarySearchLeftBorder(qq, 4));
		System.out.println(p.binarySearchRightBorder(qq, 4));
		
		System.out.println("END");
				
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] readString = br.readLine().split(" ");
		int n = Integer.parseInt(readString[0].trim());
		int m = Integer.parseInt(readString[1].trim());
		int[] leftPoint = new int[n];
		int[] rightPoint = new int[n];
		int x;
		int y;
		for (int i = 0; i < n; i++) {
			readString = br.readLine().split(" ");
			leftPoint[i] = Integer.parseInt(readString[0].trim());
			rightPoint[i] = Integer.parseInt(readString[1].trim());
		}

		// сортировка левых точек отрезка
		quick3Sort(leftPoint);
		// сортировка правых точек отрезка
		quick3Sort(rightPoint);

		StringBuilder sb = new StringBuilder(m + m);
		readString = br.readLine().split(" ");
		for (int i = 0; i < readString.length; i++) {
			x = Integer.parseInt(readString[i].trim());
			y = 0;
			int leftBorder = 0;
			int rightBorder = 0;
			// бинарный поиск точки на отрезке
			// поиск по левым точкам отрезков (заведомо большие начальные точки
			// отрезка -> точка не попадает)
			// Возвращает число точек меньшее или равное значению текущей точке
			leftBorder = binarySearch(leftPoint, x);
			// поиск по правым точкам отрезков (заведомо меньшие конечные точки
			// отрезка -> точка не попадает)
			// Возвращает число точек большее или равное значению текущей точке
			rightBorder = binarySearchReverse(rightPoint, x);

			if (leftBorder < 0 && rightBorder < 0) {
				// нет пересечений
				y = 0;
			} else if (leftBorder < 0) {
				// Пересечение только по правой границе
				y = rightBorder;
			} else if (rightBorder < 0) {
				// Пересечение только по левой границе
				y = leftBorder;
			} else {
				// Пересечение только с отрезками (количество точек в правом
				// минус в левом)
				y = Math.abs(rightBorder - leftBorder);
			}
			sb.append(y + " ");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static void quick3Sort(int[] a) {
		quick3Sort(a, 0, a.length - 1);
	}

	// ВНИМАНИЕ - выбираем первый элемент!!! Если массив не перемешан, то может
	// быть худший случай
	private static void quick3Sort(int[] a, int l, int r) {
		if (l >= r) {
			return;
		}
		int lt = l;
		int gt = r;
		int x = a[l];
		int i = l;

		while (i <= gt) {
			int cmp = Integer.compare(a[i], x);
			if (cmp < 0) {
				int t = a[lt];
				a[lt] = a[i];
				a[i] = t;
				lt++;
				i++;
			} else if (cmp > 0) {
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

	private static int binarySearch(int[] a, int key) {
		int l = 0;
		int r = a.length - 1;
		while (r - l > 1) {
			int med = l + (r - l) / 2;
			if (Integer.compare(a[med], key) <= 0) {
				l = med;
			} else {
				r = med;
			}
		}

		for (int i = l; i <= r; i++) {
			if (Integer.compare(a[i], key) > 0) {
				return i;
			}
			if (i == r) {
				// Выходим за границу справа, берем правый элемент
				return i + 1;
			}

		}
		return -1;
	}

	private static int binarySearchReverse(int[] a, int key) {
		int l = 0;
		int r = a.length - 1;
		while (r - l > 1) {
			int med = l + (r - l) / 2;
			if (Integer.compare(a[med], key) == 0) {
				r = med;
			} else if (Integer.compare(a[med], key) < 1) {
				l = med;
			} else {
				r = med;
			}
		}

		for (int i = r; i >= l; i--) {
			if (Integer.compare(a[i], key) < 0) {
				return i + 1;
			}
			if (i == l) {
				// Выходим за границу слева, берем левый элемент
				return i;
			}
		}
		return -1;
	}

	
	
	private int binarySearchLeftBorder(int[] a, int x) {
		int l = -1;
		int r = a.length;
		//a[l] < x
		//a[r] >=x
		while (r > l + 1) {
			int m = l + ((r - l) >> 1);
			if (a[m] < x) {
				l = m;
			} else {
				r = m;
			}
		}
		/*
		Если оставить только return r + 1;
		то вернет первый номер элемента слкдующего после искомого элемента,
		либо соотвествующий ему
		 */
		if (r < a.length && a[r] == x) {
			return r + 1;
		} else {
			return -1;
		}	
	}
	
	private int binarySearchRightBorder(int[] a, int x) {
		int l = -1;
		int r = a.length;
		//a[l] <= x
		//a[r] > x
		while (r > l + 1) {
			int m = l + ((r - l) >> 1);
			if (a[m] <= x) {
				l = m;
			} else {
				r = m;
			}
		}
		/*
			Если оставить только return l + 1;
			то вернет последний номер элемента предшествующий искомому элементу,
			либо соотвествующий ему
			
		*/
		if (l >= 0 && a[l] == x) {
			return l + 1;
		} else {
			return -1;
		}
		
	}
}
