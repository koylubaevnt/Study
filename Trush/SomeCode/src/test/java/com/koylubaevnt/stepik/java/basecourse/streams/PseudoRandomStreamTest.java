package com.koylubaevnt.stepik.java.basecourse.streams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.stream.IntStream;

import org.junit.Test;

public class PseudoRandomStreamTest {

	@Test
	public void test1() {
        IntStream stream = PseudoRandomStream.pseudoRandomStream(13);
        stream = stream.limit(15);
        assertNotEquals(null, stream);
	}
	
	@Test
	public void test2() {

        IntStream stream = PseudoRandomStream.pseudoRandomStream(13);
        stream = stream.limit(15);
        assertNotEquals(null, stream);
        stream.close();
        
	}
	
	@Test
	public void test3() {

        String expected = "13 16 25 62 384 745 502 200 0 0 0 0 0 0 0 ";
        StringBuffer value = new StringBuffer();

        IntStream stream = PseudoRandomStream.pseudoRandomStream(13);
        stream = stream.limit(15);
        stream.forEach(v -> value.append(v).append(" "));
        assertEquals(expected, value.toString());
	}
}
