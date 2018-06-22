/**
 * Динамеческая связность
 * 
 * Задача:
 * 	Имеется последовательность вводимых пар чисел, где каждое число
 * 	представляет объект некоторого типа, и пара p q означает, что 
 * 	"p связан с q".
 * 
 * Программа должна отсеивать из послеовательности лишние пары 
 * (в которых оба объекта принадлежат одному и тому же классу эквивалентности)
 * 
 */
package com.koylubaevnt.algorithms.unionfind;

import java.util.Scanner;

/**
 * Объединение-поиск:
 * 	Быстрый поиск
 * 	Сложность: N^2
 * 
 * 	Неприменим для больших задач
 * 
 * @author KojlubaevNT
 *
 */
public class QuickFind {

	private int[] array;	//Узлы с целочисленными значениями ()
	private int count;		//Количество классов эквивалентности

	/**
	 * Инициализация n узлов с целочисленными именами (от 0 до n-1)
	 * 
	 * @param count количество узлов
	 */
	public QuickFind(int count) {
		this.count = count;
		this.array = new int[count];
		for(int i = 0; i < count; i++) {
			array[i] = i;
		}
	}
	
	/**
	 * Количество компонентов
	 * 
	 * @return	количество классов эквивалентности
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Идентификатор компонента для p (от 0 до n-1)
	 * 
	 * @param i номер компонента
	 * @return	значение компонента
	 */
	public int find(int i) {
		return array[i];
	}

	/**
	 * Определяет принадлежность пар значений одному компоненту

	 * @param p	первое значение из пары значений 
	 * @param q	второе значение из пары компонентов
	 * @return	принадлежит ли пара одному компоненту
	 */
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	/**
	 * Добавление соединения пар
	 * 
	 * @param p	первое значение из пары значений
	 * @param q второе значение из пары значений
	 */
	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if(pId == qId) return;
		
		for(int i = 0; i < array.length; i++) {
			if(array[i] == pId) {
				array[i] = qId;
			}
		}
		count--;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < array.length; i++) {
			sb.append(array[i]).append(" ");
		}
		return sb.toString();
	}
	
	/**
	 * Тестирвоание работы метода "Быстрый поиск"
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		QuickFind uf = new QuickFind(n);
		int p, q;
		System.out.print("p q: ");
		System.out.println(uf.toString());
		while(scanner.hasNext()) {
			p = scanner.nextInt();
			q = scanner.nextInt();
			System.out.print(p + " " + q + ": ");
			if(!uf.isConnected(p, q)) {
				uf.union(p, q);
				System.out.println(uf.toString());	
			} else {
				System.out.print(uf.toString());
				System.out.println(" - Уже связаны");
			}
			
		}
		scanner.close();

	}

}
