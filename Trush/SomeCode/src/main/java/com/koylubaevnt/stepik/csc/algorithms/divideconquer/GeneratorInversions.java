package com.koylubaevnt.stepik.csc.algorithms.divideconquer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Random;

public class GeneratorInversions {

	public static void main(String[] args) throws URISyntaxException, FileNotFoundException {
		
		PrintWriter printWriter = new PrintWriter("generate-input.txt");
		int n = 100000;
		Random random = new Random(); 
		printWriter.println(n);
		for(int i = 0; i < n; i++) {
			printWriter.print(random.nextInt() + " ");
		}
		printWriter.close();
	}

}
