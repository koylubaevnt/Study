package com.brysekkel.typeinfo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class RecoverCADState {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("CADState.out"));
		List<Class<? extends Shape>> shapeTypes = 
				(List<Class<? extends Shape>>)in.readObject();
		Line.deserializeStaticState(in);
		List<Shape> shapes = (List<Shape>)in.readObject();
		System.out.println(shapeTypes);
		System.out.println(shapes);

	}

}
