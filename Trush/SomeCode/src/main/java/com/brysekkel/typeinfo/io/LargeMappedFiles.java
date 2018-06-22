package com.brysekkel.typeinfo.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class LargeMappedFiles {

	static int length = 128 * 1024 * 1024;//0x8FFFFFF; //128 Mb
	
	public static void main(String[] args) throws Exception {
		System.out.println(length + " - " + (128 * 1024 * 1024));
		MappedByteBuffer out = new RandomAccessFile("test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
		for(int i = 0; i < length; i++)
			out.put((byte)'x');
		System.out.println("Finished writing");
		for(int i = length / 2; i < length / 2 + 6; i++)
			System.out.println((char)out.getLong(i));

	}

}
