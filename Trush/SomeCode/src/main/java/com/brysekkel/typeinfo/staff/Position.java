package com.brysekkel.typeinfo.staff;

public class Position {

	private String title;
	private Person person;
	
	public Position(String jobTitle, Person employee) {
		this.title = jobTitle;
		this.person = employee; 
	}
	
	public Position(String jobTitle) {
		this.title = jobTitle;
		this.person = Person.NULL;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		if(person == null) { 
			person = Person.NULL;
		}
		this.person = person;
	}

	@Override
	public String toString() {
		return "Position [title=" + title + ", person=" + person + "]";
	}
	
}
