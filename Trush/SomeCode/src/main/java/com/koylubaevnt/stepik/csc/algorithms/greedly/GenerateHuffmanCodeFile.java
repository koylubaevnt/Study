package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

public class GenerateHuffmanCodeFile {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
		Random random = new Random(System.currentTimeMillis());
	
		StringBuilder sb = new StringBuilder();
		sb.append(HuffmanCode.class.getPackage().getName().replace(".", File.separator))
			.append(File.separator)
			.append("input.txt");
		URI uri = HuffmanCode.class.getClassLoader().getResource(sb.toString()).toURI();
		
		PrintWriter printWriter = new PrintWriter(new File(uri).toString());
		int n = 10000;
		for(int i = 0; i < n; i++) {
			printWriter.print((char) ('a' + random.nextInt(26)));
		}
		printWriter.close();
	}

}
