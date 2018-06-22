package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Покрытие точек отрезками
 * 
 * Вход: множество n точек на прямой x1...xn (- R
 * Выход: минимальное количество отрезков единичной длинны, которыми можно покрыть все точки
 * 
 * @author KojlubaevNT
 *
 */
public class PointsCover {

	
	/**
	 * Наивный алгоритм
	 * Ищет минимальный элемент в цикле, добавляет отрезок от мин до мин + 1
	 * потом удаляет из набора покрытые этим отрезком точки в цикле.
	 * 
	 * 
	 * Время работы O(n^2)
	 * 
	 * @param s
	 * @return
	 */
	public static List<Line> naivePointsCover(int[] s) {
		// To boxed list
		Set<Integer> set = Arrays.stream( s ).boxed().collect( Collectors.toSet());
		List<Line> list = new ArrayList<>(); 
		
		while (!set.isEmpty()) {
			int min = Integer.MAX_VALUE;
			for(Integer i : set) {
				if(min > i) {
					min = i;
				}
			}
			Line line = new Line(min, ++min);
			list.add(line);
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Integer i = (Integer) iterator.next();
				if (i <= min) {
					iterator.remove();
				}
			}
		}
		return list;
	}
	
	/**
	 * Наивный усовершенствованный алгоритм 
	 * Сортирует массив используя Dual-Pivot Quicksort algorithm.
	 * 
	 * Далее начиная от левой точки строит единичный отрезок, смещает указатель массива,
	 * пока не пройдет все попавшие точки в отрезок. Берет следующий элемент и опять строит единичный отрезок
	 * и т.д. 
	 * 
	 * Время работы T(Sort) + O(n) = O(n log n) + O(n) = O(n log n) 
	 * 
	 * @param s
	 * @return
	 */
	public static List<Line> naiveImprovedPointsCover(int[] s) {
		Arrays.sort(s);
		int n = s.length;
		List<Line> list = new ArrayList<>(); 
		
		int i = 0;
		while (i < n) {
			int xi = s[i];
			int r = s[i] + 1;
			list.add(new Line(xi, r));
			//i++;
			while (i < n && xi <= r) {
				i++;
				if(i==n) {
					xi = s[i-1] + 1;
				} else {
					xi = s[i];
				}
			}
		}
		return list;
	}
	
	
	
	public static void main(String[] args) {
		int[] a = {1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,1,2,3,7,10,1,5,3,67,234,7653};
		long startTime = System.currentTimeMillis();
		List<Line> lines = naivePointsCover(a);
		System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime));
		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			Line line = (Line) iterator.next();
			System.out.println(line);
		}
		System.out.println("=======================================");
		startTime = System.currentTimeMillis();
		lines = naiveImprovedPointsCover(a);
		System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime));
		for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
			Line line = (Line) iterator.next();
			System.out.println(line);
		}

	}

}
