package com.brysekkel.typeinfo.enumerated;

import java.util.Random;

import com.brysekkel.typeinfo.generics.Generator;

//enum NotPossible extends Pet {} //Не работает

enum CartonCharacter implements Generator<CartonCharacter> {
	SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;
	private Random rand = new Random(47);
	
	@Override
	public CartonCharacter next() {
		return values()[rand.nextInt(values().length)];
	}
}

public class EnumImplementation {

	public static <T> void printNext(Generator<T> rg) {
		System.out.print(rg.next() + ", ");
	}
	
	public static void main(String[] args) {
		CartonCharacter cc = CartonCharacter.BOB;
		for(int i = 0; i < 10; i++)
			printNext(cc);
	}
}
