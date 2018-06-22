package com.koylubaevnt.stepik.java.basecourse.generics;

import java.util.Objects;

public class Pair<T, V> {

	private final T first;
	private final V second;
	
	private Pair() {
		this.first = null;
		this.second = null;
	}
	
	private Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }

	
	public T getFirst() {
		return first;
	}
	
	public V getSecond() {
		return second;
	}
	
	public static <T, V> Pair<T, V> of(T first, V second) {
        return new Pair<>(first, second);
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first)
        		&& Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first) 
        		+ Objects.hashCode(second) ;
    }

	
}
