package com.apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.apress.spring.utils.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="entry")
public class JournalEntry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title;
	private Date created;
	private String summary;
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

	public JournalEntry() { }
	
	public JournalEntry(String title, String summary, String date) throws ParseException {
		this.title = title;
		this.summary = summary;
		this.created = format.parse(date);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@JsonIgnore
	public String getCreatedAsShort() {
		return format.format(created);
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder("JournalEntry(");
		value
			.append("Id: ")
			.append(id)
			.append(",Title: ")
			.append(title)
			.append(",Summary: ")
			.append(summary)
			.append(",Created: ")
			.append(getCreatedAsShort())
			.append(")");
		
		return value.toString();
	}
	
	
}
