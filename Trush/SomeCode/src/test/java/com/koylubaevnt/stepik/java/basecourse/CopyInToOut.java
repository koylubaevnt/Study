package com.koylubaevnt.stepik.java.basecourse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

/**
 * Created by meanmail on 13.10.2016.
 * <p>
 * Этот класс переименуйте в Main
 */
public class CopyInToOut {


    public static void main(String[] args) throws IOException {

        // Код для тестирования. Перед отправкой в степик, удалите или закомиентируйте код для тестирования
        test();
        if (!testing)
            return;
        // Конец кода который не нужно отправлять в стэпик

        // Здесь поместите ваш код, запустите и код будет протестирован

    }

    // Нижележащий код для тестирования, не изменяйте его

    private static boolean testing = false;
    private static ByteArrayOutputStream outStream;
    private static final PrintStream standartOut = System.out;

    private static byte[][][] testSet = new byte[][][]{{{-1, 13, 10, 10, 13}, {-1, 10, 10, 13}}/*, {{}, {}}, {{13, 10}, {10}},
            {{10, 13}, {10, 13}}, {{13, 13, 13, 13, 13},{13, 13, 13, 13, 13}}, {{10, 10}, {10, 10}}, {{13, 13, 10}, {13, 10}},
            {{13, 65}, {13, 65}}*/};

    private static void test() throws IOException {

        if (testing)
            return;

        testing = true;

        for (int i = 0; i < testSet.length; i++) {

            testStart(i);

            main(null);

            testEnd(i);
        }

        testing = false;
    }

    // Этот метод для тестирования.
    private static void testStart(int i) throws IOException {
    	InputStream tsStream = new ByteArrayInputStream(testSet[i][0]);
    	outStream = new ByteArrayOutputStream();
        // Пересылаем тестовый набор в выходной поток
        System.setIn(tsStream);
/*
    	List<Byte> listByte = new ArrayList<>();
    	int readByte;
		int previusByte = -1;
		while((readByte = System.in.read()) != -1) {
			if (previusByte == 13 && readByte == 10) {
				listByte.remove(listByte.size() - 1);
			}
			listByte.add((byte)readByte);
			previusByte = readByte;
		}
		byte[] result = new byte[listByte.size()];
		for (int j = 0; j < listByte.size(); j++) {
			result[j] = listByte.get(j);
		}
		outStream.write(result);
		outStream.flush();
	*/
        convert(tsStream, outStream);
        System.setOut(new PrintStream(outStream));
    }

    static void convert(InputStream inputStream, OutputStream outputStream) throws IOException {
        final byte LF = 0x0A;  // '/n'
        final byte CR = 0x0D;  // '/r'
        
        int readByte = inputStream.read();
        byte curByte, prevByte = (byte) readByte;
        
        if (readByte != -1){
            while ((readByte = inputStream.read()) != -1) {
            	curByte = (byte) readByte;
            	if (prevByte != CR || curByte != LF) {
                    outputStream.write(prevByte);
                }
                prevByte = curByte;
            }
            outputStream.write(prevByte);
            outputStream.flush();
        }
    }
    
    private static void testEnd(int i) {

        System.setOut(standartOut);

        byte[] result = outStream.toByteArray();
        OutputStream s = System.out;
        assertEquals(result, testSet[i][1], "Test #" + (i + 1));
    }

    public static <T> void assertEquals(T[] value, T[] expected, String msg) {

        if (!Arrays.equals(value, expected))
            System.out.printf("%s: Error! Expected %s, got %s.\n", msg, Arrays.toString(expected), Arrays.toString(value));
        else
            System.out.printf("%s: Ok\n", msg);
    }

    public static void assertEquals(int[] value, int[] expected, String msg) {

        if (!Arrays.equals(value, expected))
            System.out.printf("%s: Error! Expected %s, got %s.\n", msg, Arrays.toString(expected), Arrays.toString(value));
        else
            System.out.printf("%s: Ok\n", msg);
    }

    public static void assertEquals(byte[] value, byte[] expected, String msg) {

        if (!Arrays.equals(value, expected))
            System.out.printf("%s: Error! Expected %s, got %s.\n", msg, Arrays.toString(expected), Arrays.toString(value));
        else
            System.out.printf("%s: Ok\n", msg);
    }



    
}
