package com.koylubaevnt.stepik.java.basecourse.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		/*
		 * byte[] input = { 48, 49, 50, 51 }; ByteArrayInputStream
		 * arrayInputStream = new ByteArrayInputStream(input);
		 * System.out.println(readAsString(arrayInputStream,
		 * StandardCharsets.US_ASCII));
		 */
		System.out.printf("%.6f", readDouble(System.in));
	}

	static void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
		final byte LF = 0x0A; // '/n'
		final byte CR = 0x0D; // '/r'

		int readByte = inputStream.read();
		byte curByte, prevByte = (byte) readByte;

		if (readByte != -1) {
			while ((readByte = inputStream.read()) != -1) {
				curByte = (byte) readByte;
				if (prevByte != CR || curByte != LF) {
					outputStream.write(prevByte);
				}
				prevByte = curByte;
			}
			outputStream.write(prevByte);
			outputStream.flush();
		}
	}

	/**
	 * Реализуйте метод, который зачитает данные из InputStream и преобразует их
	 * в строку, используя заданную кодировку.
	 * 
	 * Пример InputStream последовательно возвращает четыре байта: 48 49 50 51.
	 * Метод, вызванный для такого InputStream и кодировки ASCII, должен вернуть
	 * строку "0123".
	 * 
	 * @param inputStream
	 * @param charset
	 * @return
	 * @throws IOException
	 */
	public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
		Reader reader = new InputStreamReader(inputStream, charset);
		int readByte;
		StringBuilder sb = new StringBuilder();
		while ((readByte = reader.read()) != -1) {
			sb.append(Character.toChars(readByte));
		}
		return sb.toString();
	}

	/**
	 * Напишите программу, читающую текст из System.in и выводящую в System.out
	 * сумму всех встреченных в тексте вещественных чисел с точностью до шестого
	 * знака после запятой. Числом считается последовательность символов,
	 * отделенная от окружающего текста пробелами или переводами строк и успешно
	 * разбираемая методом Double.parseDouble.
	 * 
	 * На этот раз вам надо написать программу полностью, т.е. объявить класс (с
	 * именем Main — таково ограничение проверяющей системы), метод main,
	 * прописать все import'ы.
	 * 
	 * Sample Input 1: 
	 * 1 2 3 
	 * Sample Output 1: 
	 * 6.000000
	 * 
	 * Sample Input 2: 
	 * a1 b2 c3 
	 * Sample Output 2: 
	 * 0.000000
	 * 
	 * Sample Input 3: 
	 * -1e3 
	 * 18 .111 11bbb 
	 * Sample Output 3: 
	 * -981.889000
	 * 
	 */
	public static double readDouble(InputStream inputStream) throws IOException {
		double result = 0.0;
		try (Scanner scanner = new Scanner(inputStream)) {
			while (scanner.hasNext()) {
				if (scanner.hasNextDouble()) {
					result += scanner.nextDouble();
				} else {
					scanner.next();
				}
			}
		}
		return result;
	}
}