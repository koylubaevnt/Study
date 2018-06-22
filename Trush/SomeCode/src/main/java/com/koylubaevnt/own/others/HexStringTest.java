package com.koylubaevnt.own.others;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HexStringTest {

	static Map<String, byte[]> testData = new HashMap<>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testData.put("", new byte[0]);
		testData.put("01020D112D7F", new byte[]{1,2,13,17,45,127});
		testData.put("00FFF21180", new byte[]{0,-1,-14,17,-128});
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testToHexString() {
		for (Map.Entry<String, byte[]> entry : testData.entrySet()) {
			String expected = entry.getKey();
			String actual = HexString.toHexString(entry.getValue());
			Assert.assertEquals(expected, actual);
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		HexString.toHexString(null);
	}

}
