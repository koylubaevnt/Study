package com.brysekkel.typeinfo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TransferTo {

	public static void main(String[] args) throws Exception {
		if(args.length != 2) {
			System.out.println("arguments: sourcefile destfile");
			System.exit(1);
		}
		FileChannel 
			in = new FileInputStream(args[0]).getChannel(),
			out = new FileOutputStream(args[1]).getChannel();
		in.transferTo(0, in.size(), out);
		//out.transferFrom(in, 0, in.size());
	}

}
