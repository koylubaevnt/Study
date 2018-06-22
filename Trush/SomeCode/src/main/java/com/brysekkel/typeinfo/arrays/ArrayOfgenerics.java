package com.brysekkel.typeinfo.arrays;

import java.util.ArrayList;
import java.util.List;

public class ArrayOfgenerics {

	public static void main(String[] args) {
		List<String>[] ls;
		List[] la = new List[10];
		ls = la; //Неконтролируемое предупреждение
		ls[0] = new ArrayList<>();
		//ls[1] = new ArrayList<Integer>();//Ошибка компиляции
		
		//Проблема: List<String> подтип Object
		Object[] objects = ls;
		// Компилируется и выполняется без ошибок
		objects[1] = new ArrayList<Integer>();

		List<BerylliumSphere>[] spheres =
				new List[10];
		for(int i = 0; i < spheres.length; i++) {
			spheres[i] = new ArrayList<>();
		}
	}

}
