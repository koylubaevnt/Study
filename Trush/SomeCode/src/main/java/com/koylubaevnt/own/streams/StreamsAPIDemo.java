package com.koylubaevnt.own.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsAPIDemo {

	public static void main(String[] args) {
		StreamsAPIDemo streamsAPIDemo = new StreamsAPIDemo();
		//streamsAPIDemo.howWorkStreams();
		//streamsAPIDemo.orderProcessing();
		//streamsAPIDemo.reuseStreams();
		streamsAPIDemo.advancedOperations();
	}
	
	public void howWorkStreams() {
		List<String> mList = Arrays.asList("aa1", "cc2", "cc1", "aa2", "bb1", "AA3");
		
		System.out.println("First variant.");
		mList
			.stream()							//Получение Stream
			.filter(s -> s.startsWith("a"))		//Фильтр на выборку, только слов начинающихся на "a"
			.map(String::toUpperCase)			//Приведение всех элементов выборки к верхнему регистру
			.sorted()							//Сортировка
			.forEach(System.out::println);		//Вывод каждого элемента в конслоь
		
		System.out.println("Second variant.");
		mList
			.stream()							//Получение Stream
			.map(String::toUpperCase)			//Приведение всех элементов выборки к верхнему регистру
			.filter(s -> s.startsWith("A"))		//Фильтр на выборку, только слов начинающихся на "A"
			.sorted()							//Сортировка
			.forEach(System.out::println);		//Вывод каждого элемента в конслоь
		
		System.out.println("Third variant. First element in array: \"сс1\", \"сс2\", \"сс3\"");
		Arrays
			.asList("сс1", "сс2", "сс3")
			.stream()
			.findFirst()
			.ifPresent(System.out::println);  
		
		System.out.println("Fourth variant. No need create collection, just use Stream.of()");		
		Stream
			.of("сс1", "сс2", "сс3")
	    	.findFirst()
	    	.ifPresent(System.out::println);  
		
		//Представление цикла потоками
		System.out.println("Fifth variant. loop by Stream from 8 to 11.");		
		IntStream
			.range(8, 12)
			.forEach(System.out::println);
		
		//Представление цикла потоками
		System.out.println("Sixth variant. loop by Stream from 8 to 12.");		
		IntStream
			.rangeClosed(8, 12)
	    	.forEach(System.out::println);
		
		//преобразование поток объекта к примитивному потоку или наоборот
		System.out.println("Седьмой вариант. Преобразовнаие потока строк в поток int и выбор максимального числа");
		Stream.of("c1", "c2", "c3")
	    	.map(s -> s.substring(1))
	    	.mapToInt(Integer::parseInt)
	    	.max()
	    	.ifPresent(System.out::println);  
		
		System.out.println("Восьмой вариант. Преобразовнаие потока чисел (1..3)в поток строк");
		IntStream.range(1, 4)
	    	.mapToObj(i -> "с" + i)
	    	.forEach(System.out::println);
		
		System.out.println("Девятый вариант. Преобразовнаие потока double в поток int, а затем в поток String");
		Stream
			.of(1.0, 2.0, 3.0)
			.mapToInt(Double::intValue)
			.mapToObj(i -> "с" + i)
			.forEach(System.out::println);
	}
	
	private void orderProcessing() {
		System.out.println("Первый вариант. Порядок выполнения.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4")
			.filter(s -> {
		        System.out.println("Фильтр: " + s);
		        return true;
		    })
			.forEach(s -> System.out.println("Печать с использованием forEach: " + s));
	
		System.out.println();
		System.out.println("Второй вариант.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4")
			.map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .anyMatch(s -> {						//Операция anyMatch возвращает true, как только предикат применится к входному элементу. Это подходит для второго элемента «аа2». В связи с вертикальным исполнением цепи потока, map будет выполнен два раза.
		        System.out.println("anyMatch: " + s);
		        return s.startsWith("A");
		    });
		
		
		System.out.println();
		System.out.println("Третий вариант.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4")
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("A");
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		System.out.println();
		System.out.println("Четвертый вариант.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4")
		    .sorted((s1, s2) -> {
		        System.out.printf("sort: %s; %s\n", s1, s2);
		        return s1.compareTo(s2);
		    })
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
		
		System.out.println();
		System.out.println("Пятый вариант.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4")
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .sorted((s1, s2) -> {						//В этом примере sorted никогда не вызывали, потому что filter уменьшает входную коллекцию до одного элемента
		        System.out.printf("sort: %s; %s\n", s1, s2);
		        return s1.compareTo(s2);
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
			
		System.out.println();
		System.out.println("Шестой вариант.");
		Stream
			.of("dd2", "aa2", "bb1", "bb3", "cc4", "aa1")
		    .filter(s -> {
		        System.out.println("filter: " + s);
		        return s.startsWith("a");
		    })
		    .sorted((s1, s2) -> {						
		        System.out.printf("sort: %s; %s\n", s1, s2);
		        return s1.compareTo(s2);
		    })
		    .map(s -> {
		        System.out.println("map: " + s);
		        return s.toUpperCase();
		    })
		    .forEach(s -> System.out.println("forEach: " + s));
			
	}
	
	/*
	 * Повторное использование Потоков (Streams)
	 * 	Потоки в Java 8 не могут быть использованы повторно
	 */
	private void reuseStreams() {
		 
		/*
		 * Чтобы избежать проблемы повторного использования потоков, мы должны создать новую цепь 
		 * для каждой терминальной операции.
		 */
		 Supplier<Stream<String>> streamSupplier =
		     () -> Stream.of("dd2", "aa2", "bb1", "bb3", "cc")
		             .filter(s -> s.startsWith("a"));
		 boolean t = false;
		 System.out.println(t);
		 t = streamSupplier.get().anyMatch(s -> true);   // операция пройдет успешно
		 System.out.println(t);
		 t = false;
		 System.out.println(t);
		 t = streamSupplier.get().noneMatch(s -> false);  // здесь также все будет ok
		 System.out.println(t);
	}
	
	private void advancedOperations() {
		List<Person> persons =
			    Arrays.asList(
			        new Person("Andrew", 20),
			        new Person("Igor", 23),
			        new Person("Ira", 23),
			        new Person("Vitia", 12));

		/*
		 * Операция Collect
		 * 
		 * 	Collect является чрезвычайно полезной операцией, чтобы превратить элементы потока в 
		 * 	List, Set или Map. Collect принимает Collector, который состоит из четырех различных 
		 * 	операций: поставщик, аккумулятор, объединитель и финишер. Это звучит очень сложно, но 
		 * 	это только на первый взгляд. Фишкой Java 8 является поддержка различных встроенных 
		 * 	коллекторов через класс Collectors. Именно поэтому работа с ними будет намного проще.
		 */
		System.out.println("Операция Collect. Распростаненный случай");
		List<Person> filtered =
			    persons
			        .stream()
			        .filter(p -> p.name.startsWith("I"))	//Имя начинается на I
			        .collect(Collectors.toList());
		System.out.println(filtered);    // [Igor, Ira]
	
		System.out.println();
		System.out.println("Операция Collect. Группировка по возрасту");
		Map<Integer, List<Person>> personsByAge = persons
			    .stream()
			    .collect(Collectors.groupingBy(p -> p.age)); //группировка по возрасту
		personsByAge
		    .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		
		System.out.println();
		System.out.println("Операция Collect. Средний возраст");
		Double averageAge = persons
			    .stream()
			    .collect(Collectors.averagingInt(p -> p.age));
		System.out.println(averageAge);
		
		
		System.out.println();
		System.out.println("Операция Collect. Специальный встроенный объект со сводной статистикой");
		IntSummaryStatistics ageSummary =
			    persons
			        .stream()
			        .collect(Collectors.summarizingInt(p -> p.age));
		System.out.println(ageSummary);
		
		
		System.out.println();
		System.out.println("Операция Collect. Соединение в одну строку");
		String phrase = persons
			    .stream()
			    .filter(p -> p.age >= 18)				//Возраст более 18
			    .map(p -> p.name)						//Возврат имен
			    .collect(Collectors.joining(" и ", "В Германии ", " совершеннолетние.")); //разделитель, префикс, суффикс
		System.out.println(phrase);
		
		System.out.println();
		System.out.println("Операция Collect. Трансформация элементов потока в map");
		Map<Integer, String> map = persons
			    .stream()
			    .collect(Collectors.toMap(
			        p -> p.age,
			        p -> p.name,
			        (name1, name2) -> name1 + ";" + name2));
		System.out.println(map);
		
		System.out.println();
		System.out.println("Операция Collect. Собственный коллектор");
		/*
		 * Так как строки в Java неизменные, нам нужен вспомогательный класс StringJoiner, 
		 * чтобы коллектор мог построить нашу строку. Supplier изначально создает такой 
		 * StringJoiner с соответствующим разделителем. Accumulator используется для добавления 
		 * имени каждого человека в верхний регистр. Combiner знает как объединить два 
		 * StringJoiner в один. На последнем этапе Finisher строит желаемую строку из 
		 * StringJoiner.
		 */
		Collector<Person, StringJoiner, String> personNameCollector =
			    Collector.of(
			        () -> new StringJoiner(" | "),          // supplier
			        (j, p) -> j.add(p.name.toUpperCase()),  // accumulator
			        (j1, j2) -> j1.merge(j2),               // combiner
			        StringJoiner::toString);                // finisher
		String names = persons
		    .stream()
		    .collect(personNameCollector);
		System.out.println(names);
		
		System.out.println();
		System.out.println("Операция FlatMap.");
		/*
		 * FlatMap преобразует каждый элемент потока в поток других объектов. Таким образом, 
		 * каждый объект будет преобразован в ноль, один или несколько других объектов, 
		 * поддерживаемых потоком. 
		 */
		List<Foo> foos = new ArrayList<>();
		IntStream
		    .range(1, 4)
		    .forEach(i -> foos.add(new Foo("Foo" + i)));
		foos.forEach(f ->
		    IntStream
		        .range(1, 4)
		        .forEach(i -> f.bars.add(new Bar("Bar" + i + " <- " + f.name))));
		foos.stream()
	    	.flatMap(f -> f.bars.stream())
	    	.forEach(b -> System.out.println(b.name));
		
		System.out.println();
		System.out.println("Операция FlatMap. Упрощение");
		IntStream.range(1, 4)
		    .mapToObj(i -> new Foo("Foo" + i))
		    .peek(f -> IntStream.range(1, 4)
		        .mapToObj(i -> new Bar("Bar" + i + " <- " + f.name))
		        .forEach(f.bars::add))
		    .flatMap(f -> f.bars.stream())
		    .forEach(b -> System.out.println(b.name));
		
		System.out.println();
		System.out.println("Операция FlatMap. Использование класса Optional");
		/*
		 * Outer outer = new Outer();
		 * if (outer != null && outer.nested != null && outer.nested.inner != null) {
		 * 		System.out.println(outer.nested.inner.foo);
		 * }
		 */
		Optional.of(new Outer())
	    .flatMap(o -> Optional.ofNullable(o.nested))
	    .flatMap(n -> Optional.ofNullable(n.inner))
	    .flatMap(i -> Optional.ofNullable(i.foo))
	    .ifPresent(System.out::println);
		
		System.out.println();
		System.out.println("Операция Reduce. Самый старший человек");
		/*
		 * Reduce метод принимает функцию аккумулятора BinaryOperator. Это на самом деле 
		 * BiFunction, когда оба операнда имеют один и тот же тип, в этом случае Person. 
		 * BiFunctions похожи на Function, но принимает два аргумента. Пример функции сравнивает 
		 * людей по возрасту и возвращает самого старшего.
		 */
		persons
	    	.stream()
	    	.reduce((p1, p2) -> p1.age > p2.age ? p1 : p2) 
	    	.ifPresent(System.out::println); 
	
		/*
		 *  Reduce метод принимает идентифицирующее значение и BinaryOperator. Этот метод может 
		 *  быть использован для «создания» нового человека с агрегированным именем и возрастом 
		 *  других человек в потоке.
		 */
		Person result =
		    persons
		        .stream()
		        .reduce(new Person("", 0), (p1, p2) -> {
		            p1.age += p2.age;
		            p1.name += p2.name;
		            return p1;
		        });
		System.out.format("name=%s; age=%s\n", result.name, result.age);
		/*
		 * Reduce  метод принимает три параметра: значение идентификатора,BiFunction аккумулятор 
		 * и объединитель функции типа BinaryOperator
		 */
		Integer ageSum = persons
		    .stream()
		    .reduce(0,
		        (sum, p) -> {
		            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
		            return sum += p.age;
		        },
		        (sum1, sum2) -> {
		            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
		            return sum1 + sum2;
		        });
		/*
		Integer ageSum = persons
		    .stream()
		    .reduce(0, (sum, p) -> sum += p.age, (sum1, sum2) -> sum1 + sum2);
		    */
		System.out.println(ageSum);
		ageSum = persons
		    .parallelStream()
		    .reduce(0,
		        (sum, p) -> {
		            System.out.format("accumulator: sum=%s; person=%s\n", sum, p);
		            return sum += p.age;
		        },
		        (sum1, sum2) -> {
		            System.out.format("combiner: sum1=%s; sum2=%s\n", sum1, sum2);
		            return sum1 + sum2;
		        });
		System.out.println(ageSum);
		
		
	}
	
	//Дополнительные объекты для просмотра работы Stream API
	class Person {
	    String name;
	    int age;
	 
	    Person(String name, int age) {
	        this.name = name;
	        this.age = age;
	    }
	 
	    @Override
	    public String toString() {
	        return name;
	    }
	}
	 
	//Дополнительные объекты для просмотра работы Stream API
	class Foo {
	    String name;
	    List<Bar> bars = new ArrayList<>();
	 
	    Foo(String name) {
	        this.name = name;
	    }
	}
	 
	class Bar {
	    String name;
	 
	    Bar(String name) {
	        this.name = name;
	    }
	}
	
	//Дополнительные объекты для просмотра работы Stream API
	class Outer {
	    Nested nested;
	}
	 
	class Nested {
	    Inner inner;
	}
	 
	class Inner {
	    String foo;
	}
	
}
