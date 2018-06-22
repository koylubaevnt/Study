package com.brysekkel.utils.notmine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	
	/**
	 * Чтение всего файла как одной строки.
	 * 
	 * @param fileName - Имя файла
	 * @return Содержимое файла
	 */
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(
					new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while((s = in.readLine()) != null) {
					sb
						.append(s)
						.append("\n");
				}
			} finally {
				in.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/**
	 * Запись файла одним вызовом метода
	 * 
	 * @param fileName Имя файла
	 * @param text Содержимое для файла
	 */
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Конструктор (чтение файла с разбивкой по регулярному выражению)
	 * 
	 * @param fileName Имя файла
	 * @param splitter Регулярное выражение
	 */
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0).equals("")) remove(0);
	}
	
	/**
	 * Конструктор (обычное чтение по строкам - разделитель "\n") 
	 * 
	 * @param fileName Имя файла
	 */
	public TextFile(String fileName) {
		this(fileName, "\n");
	}
	
	/**
	 * Запись файла (данные берутся из ArrayList)
	 * 
	 * @param fileName Имя файла
	 */
	public void write(String fileName) {
		try {
			PrintWriter out = new PrintWriter(
					new File(fileName).getAbsoluteFile());
			try {
				for(String item : this)
					out.print(item);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		String file = read("src\\main\\java\\com\\brysekkel\\utils\\notmine\\TextFile.java");
		write("src\\main\\java\\com\\brysekkel\\utils\\notmine\\test.txt", file);
		TextFile text = new TextFile("src\\main\\java\\com\\brysekkel\\utils\\notmine\\test.txt");
		text.write("src\\main\\java\\com\\brysekkel\\utils\\notmine\\test2.txt");
		
		TreeSet<String> words = new TreeSet<>(new TextFile("src\\main\\java\\com\\brysekkel\\utils\\notmine\\TextFile.java",  "\\W+"));
		System.out.println(words);
		System.out.println(words.headSet("a"));
		
	}
}	  