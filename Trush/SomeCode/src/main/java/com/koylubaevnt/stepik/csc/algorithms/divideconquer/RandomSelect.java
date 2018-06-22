package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.util.Random;

public class RandomSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	static Random random = new Random(System.currentTimeMillis());
	
	private static int randomSelect(int[] a, int l, int r, int k) {
		if (l >= r) {
			return a[l];
		}
		int x = a[l + random.nextInt(r)];
		//int m = partition(a, l, r);
		int lt = l;
		int gt = r;
		int i = l;
		while(i <= gt) {
			int cmp = a[i] - x; 
			if(cmp < 0) {
				int t = a[lt];
				a[lt] = a[i];
				a[i] = t;
				lt++;
				i++;
			} else if(cmp > 0) {
				int t = a[gt];
				a[gt] = a[i];
				a[i] = t;
				gt--;
			} else {
				i++;
			}
		}
		int m1 = lt - 1;
		int m2 = gt + 1;
		
		if(l <= k && k <= m1) {
			return randomSelect(a, l, m1, k);
		} else if (m1 + 1 <= k && k <= m2) {
			return x;
		} else {
			return randomSelect(a, m2 + 1, r, k);
		}
		
	}
	
	private static int partition(int[] a, int l, int r) {
		int x = a[l];
		int j = l;
		for(int i = l + 1; i <= r; i++) {
			if (a[i] <= x) {
				j++;
				int t = a[j];
				a[j] = a[i];
				a[i] = t;
			}
		}
		int t = a[l];
		a[l] = a[j];
		a[j] = t;
		return j;
	}
}
