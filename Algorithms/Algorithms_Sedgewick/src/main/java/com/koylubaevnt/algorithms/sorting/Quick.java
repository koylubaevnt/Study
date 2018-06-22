/**
 * Сортировка
 * 
 * Процесс переупорядочивания последовательности объектов с целью разместить их в некотором логическом порядке
 * Применяется:
 * 1. Обработка транзакций
 * 2. Комбинаторной оптимизации
 * 3. Астрофизике
 * 4. Молекулярной динамике
 * 5. Лингвистики
 * 6. Прогнозирование погоды и т.д.
 * 
 * Модель стоимости сортировки: Подсчитывается сравнения и обмены. Где нет обменов - считаем обращения к массиву
 * Дополнительная память: на месте(без использования доп памяти), с использованием памяти
 * 
 */
package com.koylubaevnt.algorithms.sorting;

import java.util.Random;

/**
 * Сортировка:
 * 	Быстрая сортировка
 * 		Разбивает массив на два подмассива, с последующей независимой сортировкой полученных подмассивов 
 * 		Основа метода - процесс разбиения, который переупорядочивает массив, так чтобы
 *		выполнялось 3 условия:
 *		1. элемент a[j] находится в массиве на своем окончательном месте
 *		2. ни один элемент от a[lo]	до a[j-1] не больше, чем a[j]
 *		3. ни один элемент от a[j+1] до a[hi] не меньше, чем a[j]	
 * 
 * @author KojlubaevNT
 *
 */
public class Quick {

	private Quick() {
		
	}
	
	private static Random random;
	static {
		random = new Random(System.currentTimeMillis());
	}
	
	/**
	 * Функция алгоритма сортировки
	 * 
	 * @param a Массив для сортировки
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n-i);
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
        
		sort(a, 0, a.length - 1);
	}
	
	/**
	 * 
	 * Рекурсивная функция алгоритма сортировки (с границами низ, верх)
	 * 
	 * @param a		Массив для сортировки
	 * @param lo	Нижняя граница
	 * @param hi	Верхняя граница
	 */
	private static void sort(Comparable[] a, int lo, int hi) {
		if(lo >= hi) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j - 1);
		sort(a, j + 1, hi);
	}
	
	/**
	 * Разбиение массива на 2 части относительно "базового" элемента (слева все элементы меньше его, справа - больше)
	 * 
	 * @param a		Массив
	 * @param lo	Нижняя граница
	 * @param hi	Верхняя граница
	 * @return		Позиция базового элемента
	 */
	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		Comparable v= a[lo];
		
		while(true) {
			while(less(a[++i], v)) {
				if(i == hi) {
					break;
				}
			}
			while(less(v, a[--j])) {
				if(j == lo) {
					break;
				}
			}
			if(i >= j) break;
			exchange(a, i, j);
		}
		exchange(a, lo, j);
		return j;
	}
	
	/**
	 * Функция проверки, что элемент наименьший
	 * 
	 * @param x Первый элемент
	 * @param y Второй элемент
	 * @return 	Первый элемент меньше второго
	 */
	private static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	
	/**
	 * Функция обмена элементов в массиве
	 * 
	 * @param a Массив
	 * @param i Индекс первого элемента для обмена
	 * @param j Индекс второго элемента для обмена
	 */
	private static void exchange(Comparable[] a, int i ,int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
		
	}
	
	/**
	 * Функция вывода значений элементов массива
	 * 
	 * @param a Массив
	 */
	public static void show(Comparable[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Функция проверки массива на упорядоченность
	 * 
	 * @param a Массив
	 * @return Упорядочен ли массив
	 */
	public static boolean isSorted(Comparable[] a) {
		for(int i = 1; i < a.length; i++) {
			if(less(a[i], a[i - 1])) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Функция для тестирования класса Selection
	 * 
	 * @param args	параметры командной строки
	 */
	public static void main(String[] args) {
		String[] a = new String[10];
		Quick.show(a);
		Quick.sort(a);
		Quick.show(a);
		assert isSorted(a);
	}

}
