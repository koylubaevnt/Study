package com.koylubaevnt.stepik.java.basecourse.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Objects;

public class Animal implements Serializable  {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
    
    public static Animal[] deserializeAnimalArray(byte[] data){
    	Animal[] animals; 
    	try(ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
	    	int count = ois.readInt();
	    	animals = new Animal[count];
	    	for(int i = 0; i < count; i++) {
	    		animals[i] = (Animal) ois.readObject();
	    	}
    	} catch (IOException | ClassNotFoundException | ClassCastException | NegativeArraySizeException e) {
    		throw new IllegalArgumentException();
		}
    	return animals;
    }
    
	public static void main(String[] args) {
	}
}
