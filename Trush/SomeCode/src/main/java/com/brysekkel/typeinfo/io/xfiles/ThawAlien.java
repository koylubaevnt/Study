package com.brysekkel.typeinfo.io.xfiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ThawAlien {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream(new File("X.file")));
		Object mystery = in.readObject();
		System.out.println(mystery.getClass());
	}

}
