package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;


public class Inversions {

	//private int[] merge(int[] a, int[] b) {
	private void merge(int l, int m, int r) {
		//a[l..m - 1] a[m..r - 1] -> temp[l..r -1] -> a[l..r - 1]
		int i = l; //for a[]
		int j = m; //for b[]
		for(int  k = l; k < r; k++) {
			if (j == r || i < m && a[i] <= a[j]) {
				temp[k] = a[i];
				i++;
			} else {
				//a[i, i+1,... a.length - 1] > b[j]
				count += m - i;
				temp[k] = a[j];
				j++;
			}
		}
		System.arraycopy(temp, l, a, l, r - l);
		/*
		int i = 0; //for a[]
		int j = 0; //for b[]
		int[] res = new int[a.length + b.length];
		for(int k = 0; k < res.length; k++) {
			if (j == b.length || i < a.length && a[i] <= b[j]) {
				res[k] = a[i];
				i++;
			} else {
				//a[i, i+1,... a/length - 1] > b[j]
				count += a.length - i;
				res[k] = b[j];
				j++;
			}
		}
		return res;
		*/
	}
	
	//private int[] mergeSort(int[] a) {
	private void mergeSort(int l, int r) {
		if (r <= l + 1) return;
		// a[l..r - 1] -> a[l..m - 1] a[m..r - 1]
		int m = (l + r) >> 1;
		mergeSort(l, m);
		mergeSort(m, r);
		merge(l, m, r);
		/*
		 if (a.length == 1) return a;
		int n = a.length;
		int m = n / 2;
		int[] left = new int[m];
		int[] right = new int[n - m];
		
		System.arraycopy(a, 0, left, 0, m);
		System.arraycopy(a, m, right, 0, n - m);
		
		left = mergeSort(left);
		right = mergeSort(right);
		
		return merge(left, right);
		 */
	}
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		long startTime = System.currentTimeMillis();
		new Inversions().run();
		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime + " ms");
	}

	long count = 0;
	private int[] temp;
	private int[] a;
	private void run() throws URISyntaxException, IOException {
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(Inversions.class.getPackage().getName().replace(".", File.separator))
			.append(File.separator)
			.append("input.txt");
		URI uri = Inversions.class.getClassLoader().getResource(sb.toString()).toURI();
		*/
		String uri = "generate-input.txt";
		BufferedReader input = new BufferedReader(new FileReader(uri));
		int n = Integer.parseInt(input.readLine());
		//Scanner input = new Scanner(new File(uri));
		//int n = input.nextInt();
		String[] tokens = input.readLine().split(" ");
		a = new int[n];
		temp =  new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(tokens[i]);
			//a[i] = scanner.readInt();
		}
		mergeSort(0, n);
		//int[] sorted = mergeSort(a);
		/*
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < n; i++) {
			result.append(sorted[i]).append(" ");
			//System.out.print(sorted[i] + " ");
		}
		System.out.println(result.toString());
		*/
		System.out.println(count);
		//System.out.println();
		
	}

	
}
