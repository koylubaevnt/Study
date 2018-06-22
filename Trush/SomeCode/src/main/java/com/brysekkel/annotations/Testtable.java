package com.brysekkel.annotations;

public class Testtable {

	public void execute() {
		System.out.println("Executing..");
	}

	@Test void testExecute() { execute(); }

}
