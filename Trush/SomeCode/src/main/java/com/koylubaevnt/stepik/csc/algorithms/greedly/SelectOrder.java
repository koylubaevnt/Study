package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * Задача о выборе заявок
 * 
 * Вход: множество n отрезков на прямой.
 * Выход: максимальное количество попарно не пересекающихся отрезков 
 * 
 * @author KojlubaevNT
 *
 */
public class SelectOrder {

	/**
	 * Наивный алгоритм
	 * 
	 * Пока список не пуст:
	 * 	Получаем отрезок с минимальным правым концом
	 *  Добавляем его к решению
	 *  Выкидываем из спсика все отрезки пересекающиеся с полученным отрезком
	 * Вернуть полученное решение 
	 * 
	 * Время работы: O(n) 
	 * @param lines
	 * @return
	 */
	public static List<Line> actSel(List<Line> lines) {
		List<Line> result = new ArrayList<>();
		while (!lines.isEmpty()) {
			int min = Integer.MAX_VALUE;
			int x = 0;
			for(int i = 0; i < lines.size(); i++) {
				Line line = lines.get(i);
				if(min > line.getX2()) {
					min = line.getX2();
					x = i; 
				}
			}
			result.add(lines.get(x));
			
			for (Iterator iterator = lines.iterator(); iterator.hasNext();) {
				Line line = (Line) iterator.next();
				if(line != lines.get(x)) {
					if(lines.get(x).getX1() < line.getX2() && lines.get(x).getX2() > line.getX1() ) {
						 iterator.remove();
					}
				} else {
					iterator.remove();
				}
			}
		}
		return result;
	}
	
	/**
	 * Наивный усовершенствованный алгоритм
	 * 
	 * Сортируем список отрезков по правым концам
	 * Для всех отрезков в полученном порядке:
	 * 	Если текущий отрезок не пересекает последний добавленный, то взять его в решение
	 * 	Иначе пропустить.
	 * Вернуть полученное решение 
	 * 
	 * Время работы: T(SORT) + O(n) = O(n log n) + O(n) = O(n log n) 
	 * @param lines
	 * @return
	 */
	public static List<Line> actImprovedSel(List<Line> lines) {
		List<Line> result = new ArrayList<>();
		lines.sort(new Comparator<Line>() {
			@Override
			public int compare(Line o1, Line o2) {
				return Integer.compare(o1.getX2(), o2.getX2());
			}
		});
		
		for(int i = 0; i < lines.size(); i++) {
			Line line = lines.get(i);
			Line lastAddedLine = result.get(result.size());
			if (lastAddedLine == null || 
					line.getX1() > lastAddedLine.getX2()
					) {
				result.add(line);
				
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
