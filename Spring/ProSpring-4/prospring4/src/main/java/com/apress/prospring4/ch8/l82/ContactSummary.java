package com.apress.prospring4.ch8.l82;

import java.io.Serializable;

public class ContactSummary implements Serializable {

	private static final long serialVersionUID = -8633925544875473593L;
	
	private String firstName;
	private String lastName;
	private String homeTelNumber;
	
	public ContactSummary(String firstName, String lastName, String homeTelNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.homeTelNumber = homeTelNumber;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getHomeTelNumber() {
		return homeTelNumber;
	}


	@Override
	public String toString() {
		return "First name: " + firstName + ", Last name: " + lastName + ", Home Phone: " + homeTelNumber;
	}
	
	
}
