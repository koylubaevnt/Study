package com.brysekkel.typeinfo.enumerated;

import java.util.Random;

public enum Input {

	NICKEL(5), DIME(10), QUATER(25), DOLLAR(100),
	TOOTHPASTE(200), CHIPS(75), SODA(100), SOAP(50),
	ABORT_TRANSACTION {
		@Override
		int amount() {
			throw new RuntimeException("ABORT.amount()");
		}
	},
	STOP {
		@Override
		int amount() {
			throw new RuntimeException("STOP.amount()");
		}
	};
	int value;
	private Input() {}
	private Input(int value) { this.value = value; }
	int amount() { return value; }
	static Random random = new Random(47);
	public static Input randomSelection() {
		return values()[random.nextInt(values().length - 1)];
	}
}
