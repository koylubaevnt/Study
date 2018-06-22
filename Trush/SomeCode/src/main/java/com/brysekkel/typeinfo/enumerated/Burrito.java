package com.brysekkel.typeinfo.enumerated;

public class Burrito {

	Spiciness degree;
	
	public Burrito(Spiciness spiciness) {
		degree = spiciness;
	}
	
	@Override
	public String toString() {
		return "Burrito is " + degree;
	}

	public static void main(String[] args) {
		System.out.println(new Burrito(Spiciness.NOT));
		System.out.println(new Burrito(Spiciness.MEDIUM));
		System.out.println(new Burrito(Spiciness.HOT));
	}

}
