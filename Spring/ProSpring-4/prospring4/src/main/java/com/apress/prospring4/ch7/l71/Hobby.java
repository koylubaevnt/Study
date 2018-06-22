package com.apress.prospring4.ch7.l71;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="hobby")
public class Hobby implements Serializable {

	private static final long serialVersionUID = -6149765210319293482L;

	private String hobbyId;
	private Set<Contact> contacts = new HashSet<>();
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hobby_id")
	public String getHobbyId() {
		return hobbyId;
	}
	
	public void setHobbyId(String hobbyId) {
		this.hobbyId = hobbyId;
	}
	
	@ManyToMany
	@JoinTable(name="contact_hobby_detail",
			joinColumns=@JoinColumn(name="hobby_id"),
			inverseJoinColumns=@JoinColumn(name="contact_id"))
	public Set<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@Override
	public String toString() {
		return "Hobby: " + getHobbyId();
	}
}
