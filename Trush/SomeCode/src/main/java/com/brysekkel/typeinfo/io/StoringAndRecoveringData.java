package com.brysekkel.typeinfo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
		DataOutputStream out = new DataOutputStream(
				new BufferedOutputStream(
						new FileOutputStream("src\\main\\java\\com\\brysekkel\\typeinfo\\io\\Data.txt")));
		out.writeDouble(3.14159D);
		out.writeUTF("That was PI");
		out.writeDouble(1.41413D);
		out.writeUTF("Square root of 2");
		out.close();
		
		DataInputStream in = new DataInputStream(
				new BufferedInputStream(
						new FileInputStream("src\\main\\java\\com\\brysekkel\\typeinfo\\io\\Data.txt")));
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());
		System.out.println(in.readDouble());
		System.out.println(in.readUTF());

	}

}
