package com.brysekkel.typeinfo.io;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class Blip1 implements Externalizable {

	public Blip1() {
		System.out.println("Blip1 Constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip1 writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip1 readExternal");
	}
	
}

class Blip2 implements Externalizable {

	Blip2() {
		System.out.println("Blip2 Constructor");
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		System.out.println("Blip2 writeExternal");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		System.out.println("Blip2 readExternal");
	}
	
}

public class Blips {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Constructing objects: ");
		Blip1 blip1 = new Blip1();
		Blip2 blip2 = new Blip2();
		ObjectOutputStream o = new ObjectOutputStream(
				new FileOutputStream("Blips.out"));
		System.out.println("Saving objects:");
		o.writeObject(blip1);
		o.writeObject(blip2);
		o.close();
		
		ObjectInputStream in = new ObjectInputStream(
				new FileInputStream("Blips.out"));
		System.out.println("Recovering blip1:");
		blip1 = (Blip1)in.readObject();
		//Ошибка конструктор Blip2 default. Externalize работает с public!!!
		//System.out.println("Recovering blip2:");
		//blip2 = (Blip2)in.readObject();
	}

}
