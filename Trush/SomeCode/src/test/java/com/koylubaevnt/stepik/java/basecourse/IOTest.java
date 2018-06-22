package com.koylubaevnt.stepik.java.basecourse;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOTest {
	
    private static byte[] testSet1 = new byte[]{10, 20, 30, 50, 1, 125};
    private static byte[] testSet2 = new byte[]{111, -45, 0, -2, 58, -1};
    private static byte[] testSet3 = new byte[]{0, 0, 0, 0, 0, 0};
    private static byte[] testSet4 = new byte[]{-1, -1, -1, -1, -1, -1};
    private static byte[] testSet5 = new byte[]{0x33, 0x45, 0x01};
    private static byte[] testSet6 = new byte[]{};
    private static byte[] testSet7 = new byte[]{-1};

    public static void main(String[] args) throws IOException {

    	 Writer writer = new OutputStreamWriter(System.out, StandardCharsets.US_ASCII);
         try {
             writer.write("Ы");
             writer.flush();
         } catch (IOException ex) {
             Logger.getLogger(IOTest.class.getName()).log(Level.SEVERE, null, ex);
         }
    	
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet1)), 71);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet2)), 931);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet3)), 0);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet4)), 5397);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet5)), 71);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet6)), 0);
        assertEquals(checkSumOfStream(new ByteArrayInputStream(testSet7)), 255);

    }
	
	public static int checkSumOfStream(InputStream inputStream) throws IOException {
	    int readByte;
		int crc = 0;
		while((readByte = inputStream.read()) != -1) {
			crc = Integer.rotateLeft(crc, 1)  ^ readByte; 
	    }
		return crc;
	}

}
