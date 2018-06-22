package com.apress.prospring4.ch3.xml;

public class InjectSimpleConfig {

	private String name = "chris Schaefer";
	private int age = 32;
	private float height = 1.778f;
	private boolean programmer = true;
	private Long ageInSeconds = 1009843200L;
	
	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public float getHeight() {
		return height;
	}

	public boolean isProgrammer() {
		return programmer;
	}

	public Long getAgeInSeconds() {
		return ageInSeconds;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAgeInSeconds(Long ageInSeconds) {
		this.ageInSeconds = ageInSeconds;
	}
	
	public void setProgrammer(boolean programmer) {
		this.programmer = programmer;
	}
	
	public void setHeight(float height) {
		this.height = height;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Name: " + name + "\n" +
				"Age: " + age + "\n" +
				"AgeInSeconds: " + ageInSeconds + "\n" +
				"Height: " + height + "\n" +
				"Is Programmer?: " + programmer;
	}

	
	
}
