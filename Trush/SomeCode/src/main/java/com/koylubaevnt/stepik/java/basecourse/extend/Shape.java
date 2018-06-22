package com.koylubaevnt.stepik.java.basecourse.extend;

public abstract class Shape {

	private final Color color;
	
	public Shape(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public abstract double getArea();
}
