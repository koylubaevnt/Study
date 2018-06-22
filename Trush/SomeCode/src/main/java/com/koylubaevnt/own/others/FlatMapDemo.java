package com.koylubaevnt.own.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class FlatMapDemo {
	static class Foo {
		String name;
		List<Bar> bars = new ArrayList<>();
		
		Foo(String name) {
			this.name = name;
		}
	}
	
	static class Bar {
		String name;

		Bar(String name) {
			this.name = name;
		}
	}
	
	static class Outer {
		Nested nested;
	}
	
	static class Nested {
		Inner inner;
	}
	
	static class Inner {
		String name;
	}
	
	public static void main(String[] args) {
		// FlatMap
		List<Foo> foos = new ArrayList<>();
		
		IntStream
			.range(1, 4)
			.forEach(i -> foos.add(new Foo("Foo" + i)));
		
		foos.forEach(f -> 
				IntStream
					.range(1, 4)
					.forEach(i -> f.bars.add(new Bar("Bar" + i + "<-" + f.name))));
		
		foos.forEach( f -> {
			System.out.println(f.name);
			f.bars.forEach(s -> System.out.println(s.name));
			}
		);
		
		System.out.println("Outer -> Nested -> Inner -> name");
		System.out.println("Old realization:");
		Outer outer = new Outer();
		if(outer != null && outer.nested !=null && outer.nested.inner != null) {
			System.out.println(outer.nested.inner.name);
		}
		System.out.println("New realization (use streams):");
		Optional.of(new Outer())
			.flatMap(o -> Optional.ofNullable(o.nested))
			.flatMap(n -> Optional.ofNullable(n.inner))
			.flatMap(i -> Optional.ofNullable(i.name))
			.ifPresent(System.out::println);
		
		
	}

}
