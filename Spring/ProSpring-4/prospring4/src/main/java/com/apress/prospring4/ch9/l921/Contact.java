package com.apress.prospring4.ch9.l921;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="contact")
@NamedQueries({
	@NamedQuery(name="Contact.findAll", query="select с from Contact с"),
	@NamedQuery(name="Contact.countAll",
			query="select count(c) from Contact c")
})
public class Contact implements Serializable {

	private static final long serialVersionUID = -8779098379551940411L;
	
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
	public Contact() {
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Version
	@Column(name="version")
	public int getVersion() {
		return version;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Contact - Id: " + id + ", Version: " + version + ", First Name:" + firstName + ", Last Name: " + lastName + ", Birth Date: " + birthDate;
	}	
	
}
