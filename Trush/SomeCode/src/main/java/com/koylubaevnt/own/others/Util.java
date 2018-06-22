package com.koylubaevnt.own.others;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	static void readDir(String path, Consumer<Path> reader) {
		Path dir = Paths.get(path);
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
			for(Path file : stream) {
				if(file.toFile().isDirectory()) {
					readDir(file.toAbsolutePath().toString(), reader);
				} else {
					reader.accept(file);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static byte[] readContent(Path path) throws IOException {
		return Files.readAllBytes(path);
	}
	
	static boolean search(String line, Pattern pattern) {
		Matcher matcher = pattern.matcher(line);
		return matcher.find();
	}
	
	public static void sleepQuietly(TimeUnit unit, int timeout) {
		try {
			unit.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
