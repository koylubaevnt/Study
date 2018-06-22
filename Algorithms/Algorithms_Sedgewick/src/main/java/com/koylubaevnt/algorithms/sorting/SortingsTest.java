package com.koylubaevnt.algorithms.sorting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import com.koylubaevnt.algorithms.utils.Timer;

/**
 * Класс для тестирования сортировок
 * Выводим время выполнения сортировки для разных видов
 * 
 * @author KojlubaevNT
 *
 */
public class SortingsTest {

	public static FileInputStream openFile() {
		//String name = "src/main/java/com/koylubaevnt/algorithms/sorting/tiny.txt";
		String name = "src/main/java/com/koylubaevnt/algorithms/sorting/words3.txt";
		File file = new File(name);
		FileInputStream fis = null;
		
		if (file.exists()) {
            try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Could not open " + name);
			}
            
		}
		return fis;
	}
	
	public static void main(String[] args) {
		randomInput(5000, 100);
		
	}

	
	public static void randomInput(int n, int t) {
		double[] total = new double[] {
				0.0, 0.0, 0.0, 0.0, 
				0.0, 0.0, 0.0, 0.0,
				0.0};
		Double[] a = new Double[n];
		Double[] b;
		Random random = new Random(System.currentTimeMillis());
		Timer timer = new Timer();
		for(int i = 0; i < t; i++) {
			for(int j = 0; j < n; j++) {
				a[j] = random.nextDouble();
			}
			
			b = a.clone();
			timer.reinitializeTime();
			Selection.sort(b);
			total[0] += timer.elapsedTime();
			assert Selection.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Insertion.sort(b);
			total[1] += timer.elapsedTime();
			assert Insertion.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Shell.sort(b);
			total[2] += timer.elapsedTime();
			assert Shell.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Merge.sort(b);
			total[3] += timer.elapsedTime();
			assert Merge.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			MergeBoundUpper.sort(b);
			total[4] += timer.elapsedTime();
			assert MergeBoundUpper.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			MergeOptimized.sort(b);
			total[5] += timer.elapsedTime();
			assert MergeOptimized.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Quick.sort(b);
			total[6] += timer.elapsedTime();
			assert Quick.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Quick3Way.sort(b);
			total[7] += timer.elapsedTime();
			assert Quick3Way.isSorted(b);
			
			b = a.clone();
			timer.reinitializeTime();
			Heap.sort(b);
			total[8] += timer.elapsedTime();
			assert Heap.isSorted(b);
			
			
		}
		System.out.println("Selection: " + total[0] + " miliseconds");
		System.out.println("Insertion: " + total[1] + " miliseconds");
		System.out.println("Shell: " + total[2] + " miliseconds");
		System.out.println("Merge: " + total[3] + " miliseconds");
		System.out.println("MergeBoundUpper: " + total[4] + " miliseconds");
		System.out.println("MergeOptimize: " + total[5] + " miliseconds");
		System.out.println("Quick: " + total[6] + " miliseconds");
		System.out.println("Quick3Way: " + total[7] + " miliseconds");
		System.out.println("Heap: " + total[8] + " miliseconds");
		System.out.println();

	}
}
