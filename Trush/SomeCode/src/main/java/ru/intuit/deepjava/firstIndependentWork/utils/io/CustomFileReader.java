package ru.intuit.deepjava.firstIndependentWork.utils.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CustomFileReader {

	public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream = new FileInputStream("textfile.txt");
		DataInputStream dataInputStream = new DataInputStream(fileInputStream);
		InputStreamReader inputStreamReader = new InputStreamReader(dataInputStream, "UTF-16");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		String currentLine;
		while((currentLine = bufferedReader.readLine()) != null) {
			System.out.println(currentLine);
		}
		fileInputStream.close();
	}

}
