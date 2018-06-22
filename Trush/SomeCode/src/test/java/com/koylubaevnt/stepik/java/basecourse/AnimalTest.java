package com.koylubaevnt.stepik.java.basecourse;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.koylubaevnt.stepik.java.basecourse.io.Animal;

public class AnimalTest {

	private static Animal[] test(int count, Object[] animals) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteStream);
        out.writeInt(count);
        for (Object animal : animals) {
            out.writeObject(animal);
        }
        out.flush();
        return Animal.deserializeAnimalArray(byteStream.toByteArray());
	}
	
	/* tests phase #0: stream is correct*/
	@Test
	public void test1() throws IOException {
		Animal[] cats = new Animal[] {new Animal("Cat")};
		Animal[] animals = test(1, cats);
	    assertEquals(animals, cats);
	}

	/* tests phase #1: stream incorrect */
	@Test
	public void test2() {
		Class<?> exceptionClass = null;
		try {
			test(1, new Object[]{new Integer(100)});
		} catch (Throwable e) {
			exceptionClass = e.getClass();
		}
		assertEquals(exceptionClass, IllegalArgumentException.class);
	}
	
	/* tests phase #2: stream incorrect */
	@Test
	public void test3() {
		Animal[] cats = new Animal[] {new Animal("Cat")};
		Class<?> exceptionClass = null;
		try {
			test(-10, cats);
		} catch (Throwable e) {
			exceptionClass = e.getClass();
	    }
		assertEquals(exceptionClass, IllegalArgumentException.class);
	}

}
