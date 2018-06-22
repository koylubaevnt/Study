package com.brysekkel.typeinfo.generics;

import java.awt.Color;

interface HasColor {
	Color getColor();
}

class Colored<T extends HasColor> {
	T item;
	Colored(T item) {
		this.item = item;
	}
	T getItem() {
		return item;
	}
	Color color() {
		//ограничение extendsпозволяет вызывать метод color()!
		return item.getColor();
	}
	
}

class Dimension {
	public int x, y, z;
}

//Не работает сначала указываем класс, затем интерфейсы!
//class ColoredDimension<T extends HasColor & Dimension> {

//Множественные ограничения
class ColoredDimension<T extends Dimension & HasColor> {
	T item;
	public ColoredDimension(T item) {
		this.item = item;
	}
	T getItem() {
		return item;
	}
	Color color() {
		return item.getColor();
	}
	int getX() {
		return item.x;
	}
	int getY() {
		return item.y;
	}
	int getZ() {
		return item.z;
	}
}

interface Weight {
	int weight();
}

//Можно указать 1 класс и несколько интерфейсов
class Solid<T extends Dimension & HasColor & Weight> {
	T item;
	public Solid(T item) {
		this.item = item;
	}
	T getItem() {
		return item;
	}
	Color color() {
		return item.getColor();
	}
	int getX() {
		return item.x;
	}
	int getY() {
		return item.y;
	}
	int getZ() {
		return item.z;
	}
	int weight() {
		return item.weight();
	}
}

class Bounded extends Dimension implements HasColor, Weight {

	@Override
	public int weight() {
		return 0;
	}

	@Override
	public Color getColor() {
		return null;
	}
	
}

public class BasicBounds {

	public static void main(String[] args) {
		Solid<Bounded> solid =
			new Solid<>(new Bounded());
		solid.color();
		solid.getY();
		solid.weight();

	}

}
