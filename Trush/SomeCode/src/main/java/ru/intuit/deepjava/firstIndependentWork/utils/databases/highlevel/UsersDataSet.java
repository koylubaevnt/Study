package ru.intuit.deepjava.firstIndependentWork.utils.databases.highlevel;

public class UsersDataSet {

	private long id;
	private String name;
	
	public UsersDataSet(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public UsersDataSet(String name) {
		this.id = -1;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	
	public String getName() {
		return name;
	}
	
}
