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
 * 	Сортировка выбором
 * 		Ищется минимальный элемент и меняется местами с первым элементом,
 * 		ищется следующий минимальный элемент и меняектся местами со вторым элементом
 * Многократно выбирается наименьший из оставшихся.
 * 
 * @author KojlubaevNT
 *
 */
public class Selection {

	private Selection() {
		
	}
	
	/**
	 * Функция алгоритма сортировки
	 * 
	 * @param a Массив для сортировки
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		for(int i = 0; i < n; i++) {
			int min = i;
			for(int j = i + 1; j < n; j++) {
				if(less(a[j], a[min])) {
					min = j;
				}
			}
			exchange(a, i, min);
		}
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
		Selection.show(a);
		Selection.sort(a);
		Selection.show(a);
		assert isSorted(a);
	}

}
