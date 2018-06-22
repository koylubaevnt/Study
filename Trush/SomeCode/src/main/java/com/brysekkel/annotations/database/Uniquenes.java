package com.brysekkel.annotations.database;

public @interface Uniquenes {

	Constraints constraints()
		default @Constraints(unique = true);
}
