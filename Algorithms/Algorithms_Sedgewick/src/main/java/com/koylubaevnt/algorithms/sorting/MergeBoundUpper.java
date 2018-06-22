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
 * 	Сортировка Слиянием - восходящая (от маленького массива к большому)
 * 		Объединение двух упорядоченных массивов для получения одного большого 
 * 		упорядоченного массива.
 * 		Время выполнения N*log N
 * 		Доп. память: N
 * 
 * @author KojlubaevNT
 *
 */
public class MergeBoundUpper {

	private MergeBoundUpper() {
		
	}
	
	private static Comparable[] array; //Дополнительный мссив
	
	/**
	 * Функция алгоритма сортировки
	 * 
	 * @param a Массив для сортировки
	 */
	public static void sort(Comparable[] a) {
		int n = a.length;
		array = new Comparable[n];
		for (int sz = 1; sz < n; sz = sz + sz) {
			for(int lo = 0; lo < n - sz; lo += sz + sz) {
				merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, n - 1));
			}
		}
		
	}
	
	/**
	 * Функция слияния массивов (сливает массив <code>a[lo..mid]</code> с <code>a[mid+1..hi]</code>)
	 * 
	 * @param a		Массив
	 * @param lo	Нижняя граница
	 * @param mid	Средняя граница
	 * @param hi	Верхняя граница
	 */
	private static void merge(Comparable[] a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		//Копируем массив a в array (чтобы потом сравнивая 2 элемента в массиве array выбрать меньший)
		for(int k = lo; k <= hi; k++) {
			array[k] = a[k];
		}
		
		for(int k = lo; k <= hi; k++) {
			if (i > mid) {
				a[k] = array[j++];
			} else if (j > hi) {
				a[k] = array[i++];
			} else if (less(array[j], array[i])) {
				a[k] = array[j++];
			} else {
				a[k] = array[i++];
			}
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
		MergeBoundUpper.show(a);
		MergeBoundUpper.sort(a);
		MergeBoundUpper.show(a);
		assert isSorted(a);
	}

}
