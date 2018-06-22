package com.koylubaevnt.own.streams;

import java.util.Arrays;
import java.util.Scanner;

class LIS
{
    // Бинарный поиск
    static int CeilIndex(int A[], int l, int r, int key)
    {
        while (r - l > 1)
        {
            int m = l + (r - l)/2;
            if (A[m] <= key)
                r = m;
            else
                l = m;
        }
 
        return r;
    }
    static int[] d;
    static int[] prev;
    static int[] tailTable;
    
    /**
     Input:
7
5 3 4 4 4 4 2

Your output:
3
1 1 3
Correct output:
4
1 3 4 5

3
5 6 4
2
1 3
     * @return
     */
    
    static int LongestIncreasingSubsequenceLength(int A[], int size)
    {
    	prev = new int[size];
    	d = new int[size];
        tailTable   = new int[size];
        int len; 
 
        tailTable[0] = A[0];
        prev[0] = -1;
        d[0] = 1;
        len = 1;
        for (int i = 1; i < size; i++)
        {
        	prev[i] = -1;
            
        	d[i] = 1;
        	if (A[i] > tailTable[0]) {
                tailTable[0] = A[i];
            }
            else if (A[i] <= tailTable[len-1]) {
                tailTable[len++] = A[i];
                prev[i] = i - 1; 
                d[i] = d[i-1] + 1;
            }
            else {
            	int index = CeilIndex(tailTable, -1, len-1, A[i]);
                tailTable[index] = A[i];
                d[len] = d[len] + 1;
                prev[len] = index - 1;
            }
        }
 
        return len;
    }
 
    public static void main(String[] args)
    {
    	Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int a[] = new int[n];
		for(int i = 0; i < n; i++) {
			a[i] = scanner.nextInt();
		}

		/*
5
5 6 5 7 5
		 */
		
        //int A[] = {5, 3, 4, 4, 2};
        //int n = A.length;
		
		/*
		System.out.println("============================");
		test(a);
		System.out.println(Arrays.toString(d));
		System.out.println("============================");
		*/
        int ans = LongestIncreasingSubsequenceLength(a, n);
        System.out.println(ans);
        int[] l = new int[ans];
		int k = 0;
		for(int i = 1; i < n; i++) {
			if(d[i] > d[k]) {
				k = i;
			}
		}
		int j = ans - 1;
		while(k > 0) {
			l[j] = k;
			j--;
			k = prev[k];
		}
		
		for(int i = 0; i < l.length; i++) {
			System.out.print((l[i] + 1) + " ");
		}
		System.out.println();
		
        
    }
    
    //1, 3, 3, 2, 1, 2, 3, 2, 3, 3
    //7, 6, 1, 6, 4, 1, 2, 4, 10, 1
    //5 4 4 2 5, в массиве с результатами получаю не 2 4 4 5, а 2 4 5 5.
    
    /*
     Вы о языке? Java. 

Если об алгоритме - СПОЙЛЕР!

Запоминал по одной цепочке каждой длины, так, чтобы заканчивалась на максимальный элемент. Снизу вверх пытался пристроить, начиная с максимально длинной цепи. 

Вырожденные случаи - все элементы по возрастанию, помним только одну цепь - время алгоритма O(n). 

Все элементы по убыванию - помним n цепей, но поиск/построение цепи O(1) - алгоритм O(n)

Все элементы одинаковы - см по убыванию.

Рандом, по грубой прикидке, O(sqr(n) * n). Реально должно быть меньше. Хватило, на TLE не натыкался.

Доп память - под массив prev[n] и под хэшмап(инт, инт).
 
     */
    
    private static void test(int[] a) {
    	d = new int[a.length];
    	d[0] = -1;
    	for (int i = 1; i < a.length; ++i) {
    		d[i] = 1;
    	}
    	 
    	for (int i = 0; i < a.length; i++) {
    		int j = Arrays.binarySearch(a, a[i]);// (upper_bound (d.begin(), d.end(), a[i]) - d.begin());
    		 if (d[j - 1] == a[i]) {
    			 j--;
    		 }
    		//for (int j = 1; j <= a.length; j++) {
    			if (d[j - 1] < a[i] && a[i] < d[j])
    				d[j] = a[i];
    		//}
    	}
    }
    
}