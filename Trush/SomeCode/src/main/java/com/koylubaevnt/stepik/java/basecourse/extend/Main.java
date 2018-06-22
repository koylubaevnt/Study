package com.koylubaevnt.stepik.java.basecourse.extend;

public class Main {

	public static void main(String[] args) {
		Point pointCenter = new Point(0, 0);
		
		Point pointA = new Point(0, 0);
		Point pointB = new Point(1, 0);
		Point pointC = new Point(0, 1);
		
		Point pointCorner = new Point(5, 5);
		
		Circle circle = new Circle(pointCenter, 1, Color.BLACK);
		Triangle triangle = new Triangle(pointA, pointB, pointC, Color.RED);
		Square square = new Square(pointCorner, 2, Color.BLUE);
		
		//Shape shape = triangle;
		Object object = triangle;
		
		triangle = (Triangle) object;
		
		Shape[] shapes = {circle, triangle, square};
		printArrayElements(shapes);
		
		Shape maxShape = findShapeWithMaxArea(shapes);
		System.out.println("Shape with max area: " + maxShape);
	}

	private static Shape findShapeWithMaxArea(Shape[] shapes) {
		Shape maxShape = null;
		for(Shape shape : shapes) {
			if(maxShape == null || (maxShape.getArea() < shape.getArea())) {
				maxShape = shape;
			}
			
		}
		return maxShape;
	}

	private static void printArrayElements(Object[] objects) {
		
		for(int  i = 0; i < objects.length; i++) {
			System.out.println(i + ": " + objects[i]);
		}
		
	}

}
