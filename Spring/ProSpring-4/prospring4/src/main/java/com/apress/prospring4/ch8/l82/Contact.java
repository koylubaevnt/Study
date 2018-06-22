package com.apress.prospring4.ch8.l82;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="contact")
@NamedQueries({
	@NamedQuery(name="Contact.findAll", query="select с from Contact с"),
	@NamedQuery(name="Contact.findAllWithDetail",
			query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h"),
	@NamedQuery(name="Contact.findById",
			query="select distinct c from Contact c left join fetch c.contactTelDetails t left join fetch c.hobbies h where c.id=:id")
})
@SqlResultSetMapping(name="contactResult",
		entities=@EntityResult(entityClass=Contact.class))
public class Contact implements Serializable {

	private static final long serialVersionUID = -8779098379551940411L;
	
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
	private Set<ContactTelDetail> contactTelDetails = new HashSet<>();
	private Set<Hobby> hobbies = new HashSet<>();
	
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

	@OneToMany(mappedBy="contact", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<ContactTelDetail> getContactTelDetails() {
		return contactTelDetails;
	}
	
	public void setContactTelDetails(Set<ContactTelDetail> contactTelDetails) {
		this.contactTelDetails = contactTelDetails;
	}
	
	public void addContactTelDetail(ContactTelDetail contactTelDetail) {
		contactTelDetail.setContact(this);
		getContactTelDetails().add(contactTelDetail);
	}
	
	public void removeContactTelDetail(ContactTelDetail contactTelDetail) {
		getContactTelDetails().remove(contactTelDetail);
	}
	
	@ManyToMany
	@JoinTable(name="contact_hobby_detail",
			joinColumns=@JoinColumn(name="contact_id"),
			inverseJoinColumns=@JoinColumn(name="hobby_id"))
	public Set<Hobby> getHobbies() {
		return hobbies;
	}
	
	public void setHobbies(Set<Hobby> hobbies) {
		this.hobbies = hobbies;
	}
	
	@Override
	public String toString() {
		return "Contact - Id: " + id + ", Version: " + version + ", First Name:" + firstName + ", Last Name: " + lastName + ", Birth Date: " + birthDate;
	}	
	
}
