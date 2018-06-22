package com.apress.prospring4.ch8.l846;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.joda.time.DateTime;
import org.springframework.data.domain.Auditable;

@Entity
@Audited
@Table(name="contact_audit")
public class ContactAudit implements Auditable<String, Long>, Serializable {

	private static final long serialVersionUID = 5678054236644433203L;
	
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private Date birthDate;
	
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@Override
	public Long getId() {
		return this.id;
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
	
	@Column(name="birth_date")
	@Temporal(TemporalType.DATE)
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	@Transient
	@Override
	public boolean isNew() {
		if(id == null) {
			return true;
		} else {
			return false;
		}
	}

	@Column(name="created_by")
	@Override
	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="last_modified_by")
	@Override
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name="created_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Override
	public DateTime getCreatedDate() {
		return createdDate;
	}

	@Override
	public void setCreatedDate(DateTime creationDate) {
		this.createdDate = creationDate;		
	}

	@Column(name="last_modified_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@Override
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}

	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate; 
	}

	@Override
	public String toString() {
		return "Contact - Id: " + id + ", Version: " + version + ", First name: " + firstName + ", Last name: " + lastName
				+ ", Birth date: " + birthDate + ", Created by: " + createdBy + ", Created date: " + createdDate
				+ ", Last modified by=" + lastModifiedBy + ", Last modified date: " + lastModifiedDate;
	}

	
	
}
