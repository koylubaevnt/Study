package com.koylubaevnt.own.others;

import java.util.Arrays;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		System.out.println("1) ==============================");
		Arrays.asList("a1","a2","b1", "c2", "c1")
			.parallelStream()
			.filter(f -> {
				System.out.format("filter: %s [%s]\n", f,
						Thread.currentThread().getName());
				return true;
			})
			.map(m -> {
				System.out.format("map: %s [%s]\n", m,
						Thread.currentThread().getName());
				return m.toUpperCase();
			})
			.forEach(s -> {
				System.out.format("forEach: %s [%s]\n", s,
						Thread.currentThread().getName());
			});
		
		System.out.println("2) ==============================");
		Arrays.asList("a1","a2","b1", "c2", "c1")
		.parallelStream()
		.filter(f -> {
			System.out.format("filter: %s [%s]\n", f,
					Thread.currentThread().getName());
			return true;
		})
		.map(m -> {
			System.out.format("map: %s [%s]\n", m,
					Thread.currentThread().getName());
			return m.toUpperCase();
		})
		.sorted((s1, s2) -> {
			System.out.format("sorted: %s <> %s [%s]\n", s1, s2,
					Thread.currentThread().getName());
//			try {
//				TimeUnit.SECONDS.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			return s1.compareTo(s2);
		})
		.forEach(s -> {
			System.out.format("forEach: %s [%s]\n", s,
					Thread.currentThread().getName());
		});
		
	}

}
