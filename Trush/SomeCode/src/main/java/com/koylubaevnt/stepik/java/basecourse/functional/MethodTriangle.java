package com.koylubaevnt.stepik.java.basecourse.functional;

import java.util.function.DoubleUnaryOperator;

public class MethodTriangle {

	private enum TypeMethodTriangle {
		LEFT,
		RIGHT,
		CENTER
	}
	
	public static double integrate(TypeMethodTriangle type, DoubleUnaryOperator f, double a, double b) {
		double result;
		switch (type) {
			case LEFT:
				result = integrateLeft(f, a, b);
				break;
			
			case RIGHT:
				result = integrateRight(f, a, b);
				break;
	
			case CENTER:
				result = integrateCenter(f, a, b);
				break;
	
			default:
				throw new IllegalArgumentException("Only: LEFT, RIGHT, CENTER value is permitted!");
		}
		return result;
	}
	
	public static double integrateLeft(DoubleUnaryOperator f, double a, double b) {
	    double step = 1e-7;
		int n = (int) java.lang.Math.round((b - a) / step);
		double result = 0.0;
		for(int i = 0; i < n; i++) {
		    result += f.applyAsDouble(a + step * i);
		}
	    return result * step;
	}
	
	public static double integrateRight(DoubleUnaryOperator f, double a, double b) {
	    double step = 1e-7;
		int n = (int) java.lang.Math.round((b - a) / step);
		double result = 0.0;
		for(int i = 1; i < n + 1; i++) {
		    result += f.applyAsDouble(step * i - a);
		}
	    return result * step;
	}
	
	public static double integrateCenter(DoubleUnaryOperator f, double a, double b) {
	    double step = 1e-7;
		int n = (int) java.lang.Math.round((b - a) / step);
		double result = 0.0;
		for(int i = 0; i < n; i++) {
		    result += f.applyAsDouble(a + step * (i + 0.5));
		}
	    return result * step;
	}
	
	public static void main(String[] args) {
		double result;
		for (TypeMethodTriangle type : TypeMethodTriangle.values()) {
			result = integrate(type, x -> x + 2, 0, 10);
			System.out.println(type + ": " + result);	
		}
		for (TypeMethodTriangle type : TypeMethodTriangle.values()) {
			result = integrate(type, x -> 1, 0, 10);
			System.out.println(type + ": " + result);
		}
		for (TypeMethodTriangle type : TypeMethodTriangle.values()) {
			result = integrate(type, x -> 2, 0, 10);
			System.out.println(type + ": " + result);
		}
	}

}
