package com.brysekkel.typeinfo.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class ViewBuffers {
	
	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.wrap(
				new byte[] { 0, 0, 0, 0, 0, 0, 0, 'a'});
		buffer.rewind();
		System.out.print("Byte Buffer ");
		while (buffer.hasRemaining()) {
			System.out.print(buffer.position() + " -> " + buffer.get() + ", ");
		}
		System.out.println();
		
		CharBuffer cb = ((ByteBuffer)buffer.rewind()).asCharBuffer();
		System.out.print("Char Buffer ");
		while (cb.hasRemaining()) {
			System.out.print(cb.position() + " -> " + cb.get() + ", ");
		}
		System.out.println();
		
		FloatBuffer fb = ((ByteBuffer)buffer.rewind()).asFloatBuffer();
		System.out.print("Float Buffer ");
		while (fb.hasRemaining()) {
			System.out.print(fb.position() + " -> " + fb.get() + ", ");
		}
		System.out.println();
		
		IntBuffer ib = ((ByteBuffer)buffer.rewind()).asIntBuffer();
		System.out.print("Int Buffer ");
		while (ib.hasRemaining()) {
			System.out.print(ib.position() + " -> " + ib.get() + ", ");
		}
		System.out.println();
		
		LongBuffer lb = ((ByteBuffer)buffer.rewind()).asLongBuffer();
		System.out.print("Long Buffer ");
		while (lb.hasRemaining()) {
			System.out.print(lb.position() + " -> " + lb.get() + ", ");
		}
		System.out.println();
		
		ShortBuffer sb = ((ByteBuffer)buffer.rewind()).asShortBuffer();
		System.out.print("Short Buffer ");
		while (sb.hasRemaining()) {
			System.out.print(sb.position() + " -> " + sb.get() + ", ");
		}
		System.out.println();
		
		DoubleBuffer db = ((ByteBuffer)buffer.rewind()).asDoubleBuffer();
		System.out.print("Double Buffer ");
		while (db.hasRemaining()) {
			System.out.print(db.position() + " -> " + db.get() + ", ");
		}
		System.out.println();
	}
}
