package com.koylubaevnt.own.others;

import java.util.Arrays;
import java.util.List;

public class Person {
	int age;
	String name;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}




	public static void main(String[] args) {
		List<Person> persons = Arrays.asList(
				new Person("Max", 18),
				new Person("Peter", 23),
				new Person("Pamela", 23),
				new Person("David", 12)
				);
		
		persons
			.parallelStream()
			.reduce(0, (sum, p) -> {
				System.out.format("accumulator: sum=%s; person=%s [%s]\n",
					sum, p, Thread.currentThread().getName());
				return sum += p.age;
			}, (sum1, sum2) -> {
				System.out.format("combiner: sum1=%s; sum2=%s [%s]\n",
						sum1, sum2, Thread.currentThread().getName());
				return sum1 + sum2;
					
			});
	}
}
