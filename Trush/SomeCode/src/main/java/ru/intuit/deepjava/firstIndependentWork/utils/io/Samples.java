package ru.intuit.deepjava.firstIndependentWork.utils.io;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;

public class Samples {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fis = new FileInputStream("/object.bz");
		BufferedInputStream bis = new BufferedInputStream(fis);
		GZIPInputStream gis = new GZIPInputStream(bis);
		ObjectInputStream ois = new ObjectInputStream(gis);
		SomeObject someObject = (SomeObject) ois.readObject();
	}
	
	private static class SomeObject implements Serializable {
		
	}
}
