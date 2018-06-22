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
 * 	Быстрая сортировка - разбиение с опетимальной энтропией
 * 			
 * 
 * @author KojlubaevNT
 *
 */
public class Quick3Way {

	private Quick3Way() {
		
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
            int r = i + random.nextInt(n-i);     // between i and n-1
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
		sort(a, 0, n);
	}
	
	/**
	 * Рекурсивная функция алгоритма сортировки (с границами низ, верх)
	 * 	Сортирует массив как 3 части относительно "базового" элемента (слева все элементы меньше его, в середине - равны, справа - больше)
	 * 
	 * @param a		Массив для сортировки
	 * @param lo	Нижняя граница
	 * @param hi	Верхняя граница
	 */
	private static void sort(Comparable[] a, int lo, int hi) {
		if(lo >= hi) return;
		int lt = lo, gt = hi;
		Comparable v = a[lo];
		int i = lo;
		while(i <= gt) {
			int cmp = a[i].compareTo(v);
			if(cmp < 0) {
				exchange(a, lt++, i++);
			} else if(cmp > 0) {
				exchange(a, i, gt--);
			} else {
				i++;
			}
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
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
		String[] a = new String[] {
				"R","B","W","W","R","W",
				"B","R","R","W","B","R"
		};
		System.out.print("lt i gt ");
		Quick3Way.show(a);
		Quick3Way.sort(a);
		Quick3Way.show(a);
		assert isSorted(a);
	}

}
