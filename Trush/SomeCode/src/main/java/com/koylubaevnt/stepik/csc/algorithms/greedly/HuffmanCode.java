package com.koylubaevnt.stepik.csc.algorithms.greedly;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Сжатие данных
 * 
 * Вход: строка s.
 * Выход: бинарный код символов строки s, обеспечивающий
 * 	кратчайшее представление s
 * 
 * s= abacabad
 * коды символов: a = 00, b = 01, c = 10, d = 11
 * закодированная строка: 0001001000010011 (16 битов) 
 *  
 *  Код Хаффмана
 *  
 *  Вход: частоты символов
 *  Выход: строго двоичное дерево (у каждой вершины либо ноль, либо два сына),
 *  листья которого помечены частотами, минимизирующее сумму частот * (глубину листа)
 *  
 * @author KojlubaevNT
 *
 */
public class HuffmanCode {

	private static class Node {
		final int frequency;
		String code;
		
		void buildCode(String code) {
			this.code = code;
		}
		
		public Node(int frequency) {
			this.frequency = frequency;
		}
	}
	
	private static class InnerNode extends Node {
		Node left;
		Node right;
		
		@Override
		void buildCode(String code) {
			super.buildCode(code);
			left.buildCode(code + "1");
			right.buildCode(code + "0");
		}
		
		public InnerNode(Node left, Node right) {
			super(left.frequency + right.frequency);
			this.left = left;
			this.right = right;
		}
	}
	
	private static class LeafNode extends Node {
		char symbol;
		
		@Override
		void buildCode(String code) {
			super.buildCode(code);
		}
		
		public LeafNode(char symbol, int frequency) {
			super(frequency);
			this.symbol = symbol;
		}
		
		
	}
	
	
	public static void huffman() throws FileNotFoundException, URISyntaxException {
		
		StringBuilder sb = new StringBuilder();
		sb.append(HuffmanCode.class.getPackage().getName().replace(".", File.separator))
			.append(File.separator)
			.append("input.txt");
		URI uri = HuffmanCode.class.getClassLoader().getResource(sb.toString()).toURI();
		
		sb = new StringBuilder();
		
		Scanner scanner = new Scanner(new File(uri));
		String s = scanner.next();
		scanner.close();
		
		Map<Character, Integer> frequrencies = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			Character key = s.charAt(i);
			if(frequrencies.containsKey(key)) {
				frequrencies.put(key, frequrencies.get(key) + 1);
			} else {
				frequrencies.put(key, 1);
			}
		}
		
		Map<Character, Node> mapCode = new HashMap<>(); 
		Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.frequency, o2.frequency);
			}
		});
		
		for(Map.Entry<Character, Integer> item : frequrencies.entrySet()) {
			LeafNode node = new LeafNode(item.getKey(), item.getValue());
			queue.add(node);
			mapCode.put(item.getKey(), node);
		}
		
		int sum = 0;
		while (queue.size() > 1) {
			Node left = queue.remove();
			Node right = queue.remove();
			InnerNode innerNode = new InnerNode(left, right);
			queue.add(innerNode);
			sum += innerNode.frequency;
		}
		if(frequrencies.size() == 1) {
			sum = s.length();
		}
		
		Node root = queue.remove();
		if(frequrencies.size() == 1) {
			root.buildCode("1");
		} else { 
			root.buildCode("");
		}
		sb.append(frequrencies.size()).append(" ").append(sum).append(System.lineSeparator());
		
		for (Entry<Character, Node> item: mapCode.entrySet()) {
			sb.append(item.getKey()).append(": ").append(item.getValue().code).append(System.lineSeparator());
		}
		for(int i = 0; i < s.length(); i++) {
			sb.append(mapCode.get(s.charAt(i)).code);
		}
		System.out.print(sb.toString());
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
		huffman();
	}
}
