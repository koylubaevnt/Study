package com.apress.prospring4.ch6.l63;

import java.io.Serializable;

public class ContactTelDetail implements Serializable {

	
	private Long id;
	private Long contactId;
	private String telType;
	private String telNumber;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
	
	public Long getContactId() {
		return contactId;
	}
	
	public void setTelType(String telType) {
		this.telType = telType;
	}
	
	public String getTelType() {
		return telType;
	}
	
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	
	public String getTelNumber() {
		return telNumber;
	}

	@Override
	public String toString() {
		return "ContactTelDetail - Id: " + id + ", ContactId: " + contactId + ", Type: " + telType + ", Number: "
				+ telNumber;
	}
	
	
	
}
