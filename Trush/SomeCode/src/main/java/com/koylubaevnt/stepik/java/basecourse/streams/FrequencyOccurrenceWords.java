package com.koylubaevnt.stepik.java.basecourse.streams;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FrequencyOccurrenceWords {

	
	/*
	 * Напишите программу, читающую из System.in текст в кодировке UTF-8, подсчитывающую в нем 
	 * частоту появления слов, и в конце выводящую 10 наиболее часто встречающихся слов.
	 * Словом будем считать любую непрерывную последовательность символов, состоящую только из 
	 * букв и цифр. Например, в строке "Мама мыла раму 33 раза!" ровно пять слов: "Мама", "мыла", 
	 * "раму", "33" и "раза".
	 * Подсчет слов должен выполняться без учета регистра, т.е. "МАМА", "мама" и "Мама" — это одно 
	 * и то же слово. Выводите слова в нижнем регистре.
	 * Если в тексте меньше 10 уникальных слов, то выводите сколько есть.
	 * Если в тексте некоторые слова имеют одинаковую частоту, т.е. их нельзя однозначно 
	 * упорядочить только по частоте, то дополнительно упорядочите слова с одинаковой частотой 
	 * в лексикографическом порядке.
	 * Задача имеет красивое решение через стримы без циклов и условных операторов. 
	 * Попробуйте придумать его.
	 * 
	 * 
	 * Мама мыла-мыла-мыла раму! 123 321 аппа
	 * 
	 * Sample Input 1:
	 * Мама мыла-мыла-мыла раму!
	 * 
	 * Sample Output 1:
	 * мыла
	 * мама
	 * раму
	 * 
	 * Sample Input 2:
	 * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at 
	 * faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit 
	 * blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc 
	 * eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing 
	 * elit. Integer vel odio nec mi tempor dignissim.
	 *
	 *	Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer vel odio nec mi tempor dignissim.
	 * 
	 * Sample Output 2:
	 * consectetur
	 * faucibus
	 * ipsum
	 * lorem
	 * adipiscing
	 * amet
	 * dolor
	 * eget
	 * elit
	 * mi
	 * 
	 */
	public static void main(String[] args) {
		new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).lines()
		.flatMap(Pattern.compile("[^a-zA-Z0-9а-яА-Я]")::splitAsStream)
		.filter(s -> !s.isEmpty())
		.map(s -> new Entry(s.toLowerCase(), 1))
		.collect(
			Collectors.collectingAndThen(
				Collectors.groupingBy(
					(p) -> p.getWord(), 
					Collectors.mapping(
						Entry::getCount,
						Collectors.counting())), 
					(map) -> map.entrySet().stream()
					.sorted((e1, e2) -> {
						int cmp = -e1.getValue().compareTo(e2.getValue());
						if(cmp == 0) {
							return e1.getKey().compareTo(e2.getKey());
						}
						return cmp;
					})
					.limit(10)
					.map(s -> s.getKey())
					.collect(Collectors.toList())
				)
		).stream().forEachOrdered(System.out::println);
		//List<String> counted =
		/*
		new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).lines()
		.flatMap(Pattern.compile("[^a-zA-Z0-9а-яА-Я]")::splitAsStream)
		.filter(s -> !s.isEmpty())
		.map(s -> new Entry(s.toLowerCase(), 1))
		.collect(
			Collectors.collectingAndThen(
				Collectors.groupingBy(
					(p) -> p.getWord(), 
					Collectors.mapping(
						Entry::getCount,
						Collectors.counting())), 
					(map) -> map.entrySet().stream()
					.sorted((e1, e2) -> {
						int cmp = -e1.getValue().compareTo(e2.getValue());
						if(cmp == 0) {
							return e1.getKey().compareTo(e2.getKey());
						}
						return cmp;
					})
					.limit(10)
					.map(s -> s.getKey())
					.collect(Collectors.toList())
				)
		).stream().forEachOrdered(System.out::println);
		*/
		/*
		//Map<Long, List<String>> 
		List<String> counted =
		new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8)).lines()
		.flatMap(Pattern.compile("[^a-zA-Z0-9а-яА-Я]")::splitAsStream)
		.filter(s -> !s.isEmpty())
		.map(s -> new Entry(s.toLowerCase(), 1))
		//.peek(System.out::println)
		.collect(
				Collectors.collectingAndThen(
						Collectors.groupingBy(
							(p) -> p.getWord(), 
							Collectors.mapping(
								Entry::getCount,
								Collectors.counting())), 
							(map) -> map.entrySet().stream()
							//.peek(System.out::println)
							.sorted((e1, e2) -> {
								int cmp = -e1.getValue().compareTo(e2.getValue());
								if(cmp == 0) {
									return e1.getKey().compareTo(e2.getKey());
								}
								return cmp;
							})
							.limit(10)
							.map(s -> s.getKey())
							.collect(Collectors.toList())
							//.collect(
							//	Collectors.groupingBy((p) -> p.getValue(),
							//			Collectors.mapping(
							//					(p) -> p.getKey(), 
							//					Collectors.toList()))
							//)
						)
				);
		*/
		/*
		Collectors.toMap(
				(Map.Entry entry) -> entry.getValue(), 
				//Collectors.mapping(
				//		(entry) -> entry.getKey(),
				//		Collectors.toCollection(LinkedList::new)),
				(Map.Entry entry) -> entry.getKey(),
				(p1, p2) -> p1, 
				LinkedList::new)
		*/
						/*
						(map)->{
					        return map.entrySet().stream(); 
					    })
					    */
						/*
						(s) -> s.entrySet().stream()
								.collect(
									(p) -> Collectors.toMap(
											p::getValue, 
											p::getKey
									)
								)
						)
						*/
		//System.out.println(counted);
		/*
		.forEach(f -> {
			for (Iterator iterator = set.iterator(); iterator.hasNext();) {
				Entry entry = (Entry) iterator.next();
				if(entry.getWord().equals(f.getWord())) {
					iterator.remove();
					f.setCount(entry.getCount() + 1);
					break;
				}
			}
			set.add(f);
		});
		*/
		//System.out.println(set);
		
			
	}
	
	/*
	private static class EntryComparator implements Comparator<Entry> {

		@Override
		public int compare(Entry o1, Entry o2) {
			return o1.compareTo(o2);
		}
		
	}*/
	
	private static class Entry/* implements Comparable<Entry>*/{
		private String word;
		private int count;
		
		public Entry(String word, int count) {
			this.word = word;
			this.count = count;
		}
		
		public int getCount() {
			return count;
		}
		
		public String getWord() {
			return word;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((word == null) ? 0 : word.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Entry other = (Entry) obj;
			if (word == null) {
				if (other.word != null)
					return false;
			} else if (!word.equals(other.word))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Entry [word=" + word + ", count=" + count + "]";
		}
		/*
		@Override
		public int compareTo(Entry o) {
			int cmpCount = -Integer.compare(count, o.getCount());
			int cmpWord = word.compareTo(o.getWord());
			if(cmpWord == 0 && cmpCount == 0) {
				return cmpWord;
			} else if(cmpWord == 0 && cmpCount != 0) {
				return cmpWord;
			}
			return cmpCount;
		}
		*/
		
	}

}
