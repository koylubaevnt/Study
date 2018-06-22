package com.apress.prospring4.ch10.l101;

import java.net.URL;

import org.joda.time.DateTime;

public class AnotherContact {

	private String firstName;
	private String lastName;
	private DateTime birthDate;
	private URL personalSite;
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public DateTime getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(DateTime birthDate) {
		this.birthDate = birthDate;
	}
	
	public URL getPersonalSite() {
		return personalSite;
	}

	public void setPersonalSite(URL personalSite) {
		this.personalSite = personalSite;
	}

	@Override
	public String toString() {
		return "AnotherContact [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", personalSite=" + personalSite + "]";
	}
	
	
	
}
