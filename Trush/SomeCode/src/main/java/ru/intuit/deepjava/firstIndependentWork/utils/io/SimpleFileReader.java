package ru.intuit.deepjava.firstIndependentWork.utils.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleFileReader {

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader("SimpleFileReader.java");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String currentLine;
		while((currentLine = bufferedReader.readLine()) != null) {
			System.out.println(currentLine);
		}
		reader.close();
	}
	
}
