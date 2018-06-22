package com.koylubaevnt.algorithms.search.utils;

import java.util.Scanner;

import com.koylubaevnt.algorithms.search.SequentialSearch;
import com.koylubaevnt.algorithms.search.interfaces.SymbolTable;

public class FrequencyCounter {
	
	public static void main(String[] args) {
		
		args[0] = "10";
		int minlen = Integer.parseInt(args[0]);
		SymbolTable<String, Integer> st = new SequentialSearch<String, Integer>();
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String word = scanner.next();
			if(word.length() < minlen) continue;
			if(!st.contains(word)) {
				st.put(word, 1);
			} else {
				st.put(word, st.get(word) + 1);
			}
		}
		
		String max = "";
		st.put(max, 0);
		for(String word : st.keys()) {
			if(st.get(word) > st.get(max)) {
				max = word;
			}
		}
		System.out.println(max + " " + st.get(max));
	}
	
}
