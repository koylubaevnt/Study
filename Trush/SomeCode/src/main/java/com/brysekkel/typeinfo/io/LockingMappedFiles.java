package com.brysekkel.typeinfo.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class LockingMappedFiles {

	static final int LENGTH = 0x8000000; //128 Mb
	static FileChannel fc;
	
	public static void main(String[] args) throws Exception {
		fc = new RandomAccessFile("test.dat", "rw").getChannel();
		MappedByteBuffer out = fc.map(FileChannel.MapMode.READ_WRITE, 0, LENGTH);
		for(int i = 0; i < LENGTH; i++)
			out.put((byte)'x');
		
		new LockAndModify(out, 0, 0 + LENGTH / 3);
		new LockAndModify(out, LENGTH / 2, LENGTH / 2 + LENGTH / 4);
	}
	
	private static class LockAndModify extends Thread {
		private ByteBuffer buffer;
		private int start, size;
		public LockAndModify(ByteBuffer buffer, int start, int end) {
			this.start = start;
			this.size = end;
			buffer.limit(end);
			buffer.position(start);
			this.buffer = buffer.slice();			
			start();
		}
		
		@Override
		public void run() {
			try {
				FileLock fl = fc.lock(start, size, false);
				System.out.println("Locked: " + start + " to " + size);
				while(buffer.position() < buffer.limit() - 1)
					buffer.put((byte)(buffer.get() + 1));
				fl.release();
				System.out.println("Released: " + start + " to " + size);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
