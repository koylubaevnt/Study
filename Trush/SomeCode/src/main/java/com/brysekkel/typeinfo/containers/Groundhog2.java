package com.brysekkel.typeinfo.containers;

public class Groundhog2 {
	protected int number;
	public Groundhog2(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Groundhog [number=" + number + "]";
	}
	@Override
	public int hashCode() {
		return number;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Groundhog2 && (number == ((Groundhog2)obj).number);
	}
}
