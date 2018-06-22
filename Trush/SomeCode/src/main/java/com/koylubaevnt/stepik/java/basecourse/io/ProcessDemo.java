package com.koylubaevnt.stepik.java.basecourse.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

public class ProcessDemo {

	public static void main(String[] args) throws Exception {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("ls", "-l")
			.directory(new File("/home/stepic/Vladykin"))
			.redirectInput(Redirect.from(new File("/dev/null")))
			.redirectOutput(Redirect.PIPE)
			.redirectError(Redirect.INHERIT);
		
		Process process = processBuilder.start();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			reader.lines().forEach(System.out::println);
		}

		int existValue = process.waitFor();
		if (existValue != 0) {
			System.err.println("Subprocess terminates abnormally");
		}
	}

}
