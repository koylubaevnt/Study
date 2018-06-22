package com.brysekkel.concurrency;

public class Fat {

	private volatile double d; //редотвращение оптимизации
	private static int counter = 0;
	private final int id = counter++;
	public Fat() {
		// Затратная, прерываемая операция
		for(int i = 0; i < 10_000; i++) {
			d += (Math.PI + Math.E) / (double) i;
		}
	}
	public void operation() { System.out.println(this); }
	@Override
	public String toString() {
		return "Fat id: " + id;
	}
}
