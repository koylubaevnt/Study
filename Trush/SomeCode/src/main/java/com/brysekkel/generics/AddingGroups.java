package com.brysekkel.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AddingGroups {

	public static void main(String[] args) {
		Collection<Integer> collection =
				new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		Integer[] moreInts = { 6, 7, 8, 9, 10 };
		collection.addAll(Arrays.asList(moreInts));
		//Работает быстрее но не сконструировать Collection
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		Collections.addAll(collection, moreInts);
		//оздает список на базе массива
		List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
		list.set(1, 99);//Менять элемент можно
		//list.add(21); //Arrays.asList - fixed size array. Ошибка времени исполнения. размер нельзя менять
	}

}
