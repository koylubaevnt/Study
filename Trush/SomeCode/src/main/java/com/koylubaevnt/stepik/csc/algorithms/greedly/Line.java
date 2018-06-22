package com.koylubaevnt.stepik.csc.algorithms.greedly;

public class Line {
	private int x1;
	private int x2;
	
	public Line(int x1, int x2) {
		this.x1 = x1;
		this.x2 = x2;
	}
	
	public int getX1() {
		return x1;
	}
	
	public int getX2() {
		return x2;
	}
	
	@Override
	public String toString() {
		return "Line [" + x1 + ", " + x2 + "]";
	}
}
