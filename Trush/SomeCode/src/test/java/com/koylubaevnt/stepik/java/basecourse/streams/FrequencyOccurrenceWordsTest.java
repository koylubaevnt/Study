package com.koylubaevnt.stepik.java.basecourse.streams;

import static org.junit.Assert.assertEquals;

import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class FrequencyOccurrenceWordsTest {

	@Test
	public void test1() {
			String input = "Мама мыла-мыла-мыла раму!";
			Set<String> result = new LinkedHashSet<>(3);
			/*
			result.add("мыла");
			result.add("мама");
			result.add("раму");
			*/
			Set<String> mySolution = new LinkedHashSet<>(3);
			
			assertEquals(result, mySolution);
	}
	
	@Test
	public void test2() {
		String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sodales consectetur purus at" +  
			 " faucibus. Donec mi quam, tempor vel ipsum non, faucibus suscipit massa. Morbi lacinia velit" +
			 " blandit tincidunt efficitur. Vestibulum eget metus imperdiet sapien laoreet faucibus. Nunc" +
			 " eget vehicula mauris, ac auctor lorem. Lorem ipsum dolor sit amet, consectetur adipiscing" +
			 " elit. Integer vel odio nec mi tempor dignissim.";
		Set<String> result = new LinkedHashSet<>(10);
		/*
		result.add("consectetur");
		result.add("faucibus");
		result.add("ipsum");
		result.add("lorem");
		result.add("adipiscing");
		result.add("amet");
		result.add("dolor");
		result.add("eget");
		result.add("elit");
		result.add("mi");
		*/
		Set<String> mySolution = new LinkedHashSet<>(10);
		
		
		
		assertEquals(result, mySolution);
	}
}
