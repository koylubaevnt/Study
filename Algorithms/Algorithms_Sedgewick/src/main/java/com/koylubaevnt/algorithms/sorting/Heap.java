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

/**
 * Сортировка:
 * 	Пирамидальная сортировка
 * 		
 * 	Все сортируемые элементы вставляются в очередь с приоритетами, ориентированную на минимум, 
 * 	Затем выбираются из нее с помощью повторного применения операции "извлечь наименьший".
 *	Состоит из 2х этапов:
 *		1. "создание пирамиды" - существующий массив преобразуется в пирамиду
 *		2. "выстраивание" - элементы извлекаются из пирамиды в порядке убывания, и получается упорядоченный результат
 *			
 * 
 * @author KojlubaevNT
 *
 */
public class Heap {

	private Heap() {
		
	}
	
	/**
	 * Функция алгоритма сортировки
	 * 
	 * @param a Массив для сортировки
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
        for(int k = n / 2; k >= 1; k--) {
        	sink(a, k, n);
        }
        while(n > 1) {
        	exchange(a, 1, n--);
        	sink(a, 1, n);
        }
	}
	
	
	/**
	 * Ниссходящее восстановление пирамидальности (погружение)
	 * 
	 * @param a Массив
	 * @param k Узел нарущающий для погружения
	 * @param n Узел до которого осуществлять погружение
	 */
	private static void sink(Comparable[] a, int k, int n) {
		while(2 * k <= n) {
			int j = 2 * k;
			if(j < n && less(a, j, j +1)) {
				j++;
			}
			if(!less(a, k, j)) {
				break;
			}
			exchange(a, k, j);
			k = j;
		}
	}
	
		
	/**
	 * Функция проверки, что элемент наименьший
	 * 
	 * @param a Массив элементов
	 * @param i Первый индекс элемента
	 * @param j Второй индекс элемента
	 * @return 	Первый элемент меньше второго
	 */
	private static boolean less(Comparable[] a, int i, int j) {
		return a[i - 1].compareTo(a[j - 1]) < 0;
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
		Comparable temp = a[i - 1];
		a[i - 1] = a[j - 1];
		a[j - 1] = temp;
		
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
		String[] a = new String[] {
				"S","O","R","T","E","X","A","M","P","L","E"
		};
		Heap.show(a);
		Heap.sort(a);
		Heap.show(a);
		assert isSorted(a);
	}

}
