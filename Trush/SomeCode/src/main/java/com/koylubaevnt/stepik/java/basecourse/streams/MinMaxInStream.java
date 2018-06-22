package com.koylubaevnt.stepik.java.basecourse.streams;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinMaxInStream {

	/*
	 * Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным 
	 * Comparator'ом.
	 * 
	 * Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:
	 * 		minMaxConsumer.accept(min, max);
	 * Если стрим не содержит элементов, то вызовите
	 * 		minMaxConsumer.accept(null, null);
	 */
	public static <T> void findMinMaxNeedMemory(
	        Stream<? extends T> stream,
	        Comparator<? super T> order,
	        BiConsumer<? super T, ? super T> minMaxConsumer) {
		
		List<? extends T> list = stream.sorted(order).collect(Collectors.toCollection(LinkedList::new));
		T min = list.size() > 0 ? list.get(0) : null;
		T max = list.size() > 0 ? list.get(list.size() - 1) : null;
		minMaxConsumer.accept(min, max);
	    
	}
	
	public static <T> void findMinMax(
	        Stream<? extends T> stream,
	        Comparator<? super T> order,
	        BiConsumer<? super T, ? super T> minMaxConsumer) {

	    MinMaxFinder<T> minMaxFinder = new MinMaxFinder<>(order);
	    stream.forEach(minMaxFinder);
	    minMaxConsumer.accept(minMaxFinder.min, minMaxFinder.max);
	}
	
	private static class MinMaxFinder<T> implements Consumer<T> {

	    private final Comparator<? super T> order;
	    T min;
	    T max;

	    private MinMaxFinder(Comparator<? super T> order) {
	        this.order = order;
	    }

	    @Override
	    public void accept(T t) {
	        if (min == null || order.compare(t, min) < 0) {
	            min = t;
	        }
	        if (max == null || order.compare(max, t) < 0) {
	            max = t;
	        }
	    }
	}
	
}
