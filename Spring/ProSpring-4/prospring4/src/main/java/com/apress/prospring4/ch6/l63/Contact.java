package com.apress.prospring4.ch6.l63;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Contact implements Serializable {

	private Long id;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private List<ContactTelDetail> contactTelDetails;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setContactTelDetails(List<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}
	
	public List<ContactTelDetail> getContactTelDetails() {
		return contactTelDetails;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Contact - Id: " + id + ", FirstName: " + firstName + ", LastName: " + lastName + ", BirthDate: " + birthDate;
	}
	
	
	
}
