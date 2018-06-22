package com.koylubaevnt.stepik.java.basecourse.io;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class SerializationDemo {

	public static void main(String[] args) throws Exception {
		Client client = new Client();
		client.setId(1L);
		client.setName("Chuck Norris");
		client.setBirthDate(LocalDate.of(1940, 3, 10));
		client.getAgeInYears();
		
		Path path = Paths.get("object.bin");
		try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))) {
			oos.writeObject(client);
		}
		
		Client deserializedClient;
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path))) {
			deserializedClient = (Client) ois.readObject();
		}
		
		System.out.println(client + " " + client.hashCode());
		System.out.println(deserializedClient + " " + deserializedClient.hashCode());
		
	}
	
	public static class Client implements Serializable {
		private long id;
		private String name;
		private LocalDate birthDate;
		private transient int ageInYears;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public LocalDate getBirthDate() {
			return birthDate;
		}
		public void setBirthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
		}
		public int getAgeInYears() {
			if(ageInYears == 0) {
				ageInYears = birthDate.until(LocalDate.now()).getYears();
			}
			return ageInYears;
		}
		
		@Override
		public String toString() {
			return "Client [id=" + id 
					+ ", name=" + name 
					+ ", birthDate=" + birthDate 
					+ ", ageInYears=" + ageInYears
					+ "]";
		}
		
		
		
	}
}
