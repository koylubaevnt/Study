package com.koylubaevnt.own.others;

import static java.util.Collections.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaDemo {

	public static void main(String[] args) {
		List<String> names = 
				Arrays.asList("name", "peter", "olga", "abc");

		Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		
		Collections.sort(names, 
				(String o1, String o2) -> {
					return o1.compareTo(o2);
				});
		
		//import static java.util.Collections.sort
		sort(names, 
				(String o1, String o2) -> o1.compareTo(o2));
		
		Converter<String, Integer> converter =
				(from) -> Integer.valueOf(from);
		//Если метод принимает 1 параметр, то можно передать просто этот метод!!!
		Converter<String, Integer> converter2 =
		Integer::valueOf;
				
		Integer converted = converter.convert("123");
		System.out.println(converted);
		
		Something something = new Something();
		Converter<String, String> conv = something::startsWith;
		
		String convert = conv.convert("Java");
		System.out.println(convert);
		conv = something::endsWith;
		convert = conv.convert("Java");
		System.out.println(convert);
		
		System.out.println("Predicate ========================");
		//Predicate - лямбда функция возвращающая булевое значение. Принимает что угодно
		Predicate<String> predicate = (s) -> s.length() > 0;
		System.out.println("sss: " + predicate.test("sss"));
		System.out.println("null: " + predicate.test(""));
		
		System.out.println("Optional ========================");
		//Optional - обертка над объектом, призванная исключить NullpointerException
		Optional<String> optional = Optional.of("bam");
		System.out.println(optional.isPresent());
		System.out.println(optional.get());
		System.out.println(optional.orElse("fallback"));
		optional.ifPresent((s) -> System.out.println(s.charAt(0)));
		
		// Монада - функция трансформирующая объект А в В, отделяющая объявление от исполнения.
		//	Принимает объект А, возвращает тип В.
		
		// stream - обертка над коллекцией с набором удобных методов-итераторов возвращающих stream
		// 	map, reduce, flatmap, filter, foreach ...
		List<String> myList = Arrays.asList("a1","a2","b1","c2","c1");
		
		Stream<String> stream = 
			myList
				.stream()
				.filter(s -> s.startsWith("c"))// монада
				.map(String::toUpperCase)// монада
				.sorted();// монада
		
		stream.forEach(System.out::println);
		
		Arrays.asList("a1","a2","a3")
			.stream()
			.findFirst()
			.ifPresent(System.out::println);
		
		Stream.of("a1","a2","a3")
			.findFirst()
			.ifPresent(System.out::println);
		
		IntStream
			.range(1, 4)
			.forEach(System.out::println);
		
		IntStream
			.range(1, 4)
			.mapToObj(i -> "ac" + i)
			.forEach(System.out::println);
		
		//Порядок выполнения
		//map выполнится 5 раз
		Stream.of("d2","a2","b1", "b3", "c")
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		})
		.filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("A");
		})
		.forEach(s -> System.out.println("foEach: " + s));
		
		//map выполнится 1 раз
		Stream.of("d2","a2","b1", "b3", "c")
		.filter(s -> {
			System.out.println("filter: " + s);
			return s.startsWith("a") || s.startsWith("A");
		})
		.map(s -> {
			System.out.println("map: " + s);
			return s.toUpperCase();
		})
		.forEach(s -> System.out.println("foEach: " + s));
		
		
	}

	
	
	@FunctionalInterface
	interface Converter<F, T> {
		T convert (F from);
		
	}
	
	static class Something {
		String startsWith(String s) {
			return String.valueOf(s.charAt(0));
		}
		
		String endsWith(String s) {
			return String.valueOf(s.charAt(s.length() - 1));
		}
	}
	
}
