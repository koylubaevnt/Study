package com.koylubaevnt.stepik.csc.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static class Segment {
		public int begin;
		public int end;

		public Segment(int begin, int end) {
			super();
			this.begin = begin;
			this.end = end;
		}

	}
	/**
	 * Задача на программирование: покрыть отрезки точками
	 * 
	 * По данным n отрезкам необходимо найти множество точек 
	 * минимального размера, для которого каждый из отрезков 
	 * содержит хотя бы одну из точек.
	 * 
	 * В первой строке дано число 1 ≤ n ≤ 100 отрезков. Каждая из 
	 * последующих n строк содержит по два числа 0 ≤ l ≤ r ≤ 109, 
	 * задающих начало и конец отрезка. Выведите оптимальное число 
	 * m точек и сами m точек. Если таких множеств точек несколько, 
	 * выведите любое из них.
	 * 
	 *  Sample Input 1:
	 *  3
	 *  1 3
	 *  2 5
	 *  3 6
	 *  
	 *  Sample Output 1:
	 *  1
	 *  3
	 *  
	 *  Sample Input 2:
	 *  4
	 *  4 7
	 *  1 3
	 *  2 5
	 *  5 6
	 *  
	 *  Sample Output 2:
	 *  2
	 *  3 6 
	 */
	public static void coverPointSegment() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int beginPoint, endPoint;
		Segment[] segment = new Segment[n];
		for (int i = 0; i < n; i++) {
			beginPoint = scanner.nextInt();
			endPoint = scanner.nextInt();
			segment[i] = new Segment(beginPoint, endPoint);
		}
		scanner.close();
		
		Arrays.sort(segment, new Comparator<Segment>() {
			@Override
			public int compare(Segment o1, Segment o2) {
				return Integer.compare(o1.end,o2.end);
			}
		});
		
		List<Integer> pointList = new ArrayList<Integer>(); 
		for(int i = 0; i < segment.length; i++) {
			pointList.add(segment[i].end);
			int j = segment[i].end;
			while(i + 1 < segment.length && j >= segment[i + 1].begin) {
				i++;
			}
		}
		System.out.println(pointList.size());
		for(int i = 0; i < pointList.size(); i++) {
			System.out.print(pointList.get(i) + " ");
		}
	}
	
	/**
	 * Задача на программирование: непрерывный рюкзак
	 * 
	 * 	Первая строка содержит количество предметов 1 ≤ n ≤ 10^3
	 *  и вместимость рюкзака 0 ≤ W ≤ 2⋅10^6. Каждая из следующих n 
	 *  строк задаёт стоимость 0 ≤ ci ≤ 2⋅10^6 и объём 0 < wi ≤ 2⋅10^6
	 *  предмета (n, W, ci, wi — целые числа). Выведите максимальную 
	 *  стоимость частей предметов (от каждого предмета можно 
	 *  отделить любую часть, стоимость и объём при этом 
	 *  пропорционально уменьшатся), помещающихся в данный рюкзак, 
	 *  с точностью не менее трёх знаков после запятой.
	 *  
	 *  Sample Input:
	 *  3 50
	 *  60 20
	 *  100 50
	 *  120 30
	 *  
	 *  Sample Output:
	 *  180.000
	 *  
	 *  2 25
	 *  250 23
	 *  120 11
	 *  
	 *  272.173
	 *  
	 * @param args
	 */
	public static void continuousBackpack() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int W = scanner.nextInt();
		Item[] items = new Item[n];
		
		int c;
		int w;
		for(int i = 0; i < n; i++) {
			c = scanner.nextInt();
			w = scanner.nextInt();
			items[i] = new Item(c, w);
		}
		scanner.close();
		
		Arrays.sort(items, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return -Double.compare(1.0 * o1.c / o1.w, 1.0 * o2.c / o2.w);
			}
		});
		
		c = 0;
		double sum = 0.0;
		Item item;
		while(W > 0 && c < n) {
			item = items[c];
			w = W - item.w;
			if(w >= 0) {
				W -= item.w;
				sum += item.c;
			} else {
				w = item.w - (item.w - W);
				W -= w;
				sum += 1.0 * item.c / item.w * w;
			}
			c++;
		}
		System.out.printf("%.3f", sum);
		
	}
	
	private static class Item {
		int c;
		int w;
		
		public Item(int c, int w) {
			super();
			this.c = c;
			this.w = w;
		}
		
		
	}
	
	/**
	 * Задача на программирование: различные слагаемые
	 * 
	 * 	По данному числу 1 ≤ n ≤ 109 найдите максимальное число k, для которого n можно 
	 * 	представить как сумму k различных натуральных слагаемых. Выведите в первой строке 
	 * 	число k, во второй — k слагаемых.
	 * 
	 * 	Sample Input 1:
	 * 	4
	 * 	
	 * Sample Output 1:
	 * 	2
	 * 	1 3
	 * 
	 * 	Sample Input 2:
	 * 	6
	 * 
	 * 	Sample Output 2:
	 * 	3
	 * 	1 2 3
	 *  
	 */
	public static void variousTerms() {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.close();
		
		int sum = 0;
		Stack<Integer> stack = new Stack<>();
		for(int i = 1; sum < n; i++) {
			if(sum + i <= n) {
				sum += i;
				stack.push(i);				
			} else {
				int t = stack.pop();
				stack.push(i);
				sum = sum - t + i;
			}
		}
		
		System.out.println(stack.size());
		for (Iterator iterator = stack.iterator(); iterator.hasNext();) {
			System.out.print(iterator.next() + " ");
		}
	}
	
	/**
	 * Задача на программирование: кодирование Хаффмана
	 * 
	 * 	По данной непустой строке s длины не более 10^4, состоящей из строчных 
	 * 	букв латинского алфавита, постройте оптимальный беспрефиксный код. В 
	 * 	первой строке выведите количество различных букв k, встречающихся в 
	 * 	строке, и размер получившейся закодированной строки. В следующих k 
	 * 	строках запишите коды букв в формате "letter: code". В последней 
	 * 	строке выведите закодированную строку.
	 * 
	 *  Sample Input 1:
	 *  a
	 *  
	 *  Sample Output 1:
	 *  1 1
	 *  a: 0
	 *  0
	 *  
	 *  Sample Input 2:
	 *  abacabad
	 *  
	 *  Sample Output 2:
	 *  4 14
	 *  a: 0
	 *  b: 10
	 *  c: 110
	 *  d: 111
	 *  01001100100111
	 * 
	 */
	public static void huffmanCode() {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		scanner.close();
		StringBuilder sb = new StringBuilder();
		
		Map<Character, Integer> mapCharacterCounts = new HashMap<>();
		for(int i = 0; i < s.length(); i++) {
			Character key = s.charAt(i);
			if(mapCharacterCounts.containsKey(key)) {
				mapCharacterCounts.put(key, mapCharacterCounts.get(key) + 1);
			} else {
				mapCharacterCounts.put(key, 1);
			}
			
		}
		Map<Character, Node> mapCharacterNode = new HashMap<>(); 
		Queue<Node> priorityQueue = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.frequency, o2.frequency);
			}
		});
		
		for(Map.Entry<Character, Integer> item : mapCharacterCounts.entrySet()) {
			LeafNode node = new LeafNode(item.getKey(), item.getValue());
			priorityQueue.add(node);
			mapCharacterNode.put(item.getKey(), node);
		}
		
		int sum = 0;
		while (priorityQueue.size() > 1) {
			Node left = priorityQueue.remove();
			Node right = priorityQueue.remove();
			InnerNode innerNode = new InnerNode(left, right);
			priorityQueue.add(innerNode);
			sum += innerNode.frequency;
		}
		if(mapCharacterCounts.size() == 1) {
			sum = s.length();
		}
		sb.append(mapCharacterCounts.size()).append(" ").append(sum).append(System.lineSeparator());
		
		Node root = priorityQueue.remove();
		if(mapCharacterCounts.size() == 1) {
			root.buildCode("1");
		} else { 
			root.buildCode("");
		}
		for (Entry<Character, Node> item: mapCharacterNode.entrySet()) {
			sb.append(item.getKey()).append(": ").append(item.getValue().code).append(System.lineSeparator());
		}
		for(int i = 0; i < s.length(); i++) {
			sb.append(mapCharacterNode.get(s.charAt(i)).code);
		}
		System.out.print(sb.toString());
	}
	
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
		
		public LeafNode(char symbol, String code) {
			super(0);
			this.symbol = symbol;
			this.code = code;
		}
		
	}
	
	
	/**
	 * Задача на программирование: декодирование Хаффмана
	 * 	
	 * 	Восстановите строку по её коду и беспрефиксному коду символов.
	 * 	В первой строке входного файла заданы два целых числа k и l через пробел 
	 * 	— количество различных букв, встречающихся в строке, и размер получившейся 
	 * 	закодированной строки, соответственно. В следующих k строках записаны коды 
	 * 	букв в формате "letter: code". Ни один код не является префиксом другого. 
	 * 	Буквы могут быть перечислены в любом порядке. В качестве букв могут 
	 * 	встречаться лишь строчные буквы латинского алфавита; каждая из этих букв 
	 * 	встречается в строке хотя бы один раз. Наконец, в последней строке записана 
	 * 	закодированная строка. Исходная строка и коды всех букв непусты. Заданный код 
	 * 	таков, что закодированная строка имеет минимальный возможный размер.
	 * 
	 * 	В первой строке выходного файла выведите строку s. Она должна состоять из 
	 * 	строчных букв латинского алфавита. Гарантируется, что длина правильного ответа 
	 * 	не превосходит 104 символов.
	 * 
	 *  Sample Input 1:
	 *  1 1
	 *  a: 0
	 *  0
	 *  
	 *  Sample Output 1:
	 *  a
	 *  
	 *  Sample Input 2:
	 *  4 14
	 *  a: 0
	 *  b: 10
	 *  c: 110
	 *  d: 111
	 *  01001100100111
	 *  
	 *  Sample Output 2:
	 *  abacabad
	 *  
	 */
	public static void huffmanDecode() {
		Scanner scanner = new Scanner(System.in);
		final int k = scanner.nextInt(); 	//количество букв
		final int l = scanner.nextInt();	//размер получившейся закодированной строки
		final Map<Character, String> mapCode = new HashMap<>();
		scanner.nextLine();					//прочитаем конец строки...
		for(int i = 0; i < k; i++) {
			String str = scanner.nextLine();
			StringTokenizer stringTokenizer = new StringTokenizer(str, ": ");
			Character key = stringTokenizer.nextToken().charAt(0);
			String codeString = stringTokenizer.nextToken();
			mapCode.put(key, codeString);
		}
		final String s = scanner.nextLine();	//закодированная строка
		scanner.close();
		
		StringBuilder result = new StringBuilder();
		int currentPosition = 0;
		int endPosition = l;
		while(currentPosition < endPosition) {
			for (Entry<Character, String> item : mapCode.entrySet()) {
				int length = item.getValue().length();
				if(s.length() >= currentPosition + length && s.substring(currentPosition, currentPosition + length).equals(item.getValue())) {
					result.append(item.getKey());
					currentPosition += length;
					break;
				}
			}
		}
		System.out.println(result.toString());
	}
	
	public static void main(String[] args) {
		//coverPointSegment();
		//continuousBackpack();
		//variousTerms();
		//huffmanCode();
		//huffmanDecode();
	}

	
}
