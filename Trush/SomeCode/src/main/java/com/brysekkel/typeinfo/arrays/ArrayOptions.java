package com.brysekkel.typeinfo.arrays;

import java.util.Arrays;

public class ArrayOptions {

	public static void main(String[] args) {
		BerylliumSphere[] a;
		BerylliumSphere[] b = new BerylliumSphere[5];
		System.out.println("b: " + Arrays.toString(b));
		BerylliumSphere[] c = new BerylliumSphere[5];
		for(int i = 0; i < c.length; i++) {
			if(c[i] == null) {
				c[i] = new BerylliumSphere();
			}
		}
		BerylliumSphere[] d = {
				new BerylliumSphere(),
				new BerylliumSphere(),
				new BerylliumSphere()
		};
		a = new BerylliumSphere[] {
				new BerylliumSphere(),
				new BerylliumSphere(),
		};
		System.out.println("a.length = " + a.length);
		System.out.println("b.length = " + b.length);
		System.out.println("c.length = " + c.length);
		System.out.println("d.length = " + d.length);
		a = d;
		System.out.println("a.length = " + a.length);
		
		int[] e;
		int[] f = new int[5];
		System.out.println("f: " + Arrays.toString(f));
		int[] g = new int[4];
		for(int i = 0; i < g.length; i++) {
			g[i] = i * i;
		}
		int[] h = { 11, 47, 93 };
		//Ошибка компиляции e не инициализировано
		//System.out.println("e.length = " + e.length);
		System.out.println("f.length = " + f.length);
		System.out.println("g.length = " + g.length);
		System.out.println("h.length = " + h.length);
		e = h;
		System.out.println("e.length = " + e.length);
		e = new int[] { 1, 2 };
		System.out.println("e.length = " + e.length);
		
		//массив это ссылочный тип. Изменение элемента отражается на ссылках-копиях
		BerylliumSphere[] orig = new BerylliumSphere[3];
		orig[0] = new BerylliumSphere();
		orig[1] = new BerylliumSphere();
		System.out.println("orig: " + Arrays.toString(orig));
		BerylliumSphere[] copy = orig;
		System.out.println("copy: " + Arrays.toString(copy));
		copy[2] = new BerylliumSphere();
		System.out.println("orig2: " + Arrays.toString(orig));
		System.out.println("copy2: " + Arrays.toString(copy));
		int[] origInt = new int[3];
		origInt[0] = 1;
		origInt[1] = 2;
		System.out.println("orig: " + Arrays.toString(origInt));
		int[] copyInt = origInt;
		System.out.println("copy: " + Arrays.toString(copyInt));
		copyInt[2] = 3;
		System.out.println("orig2: " + Arrays.toString(origInt));
		System.out.println("copy2: " + Arrays.toString(copyInt));
		
		
	}

}
