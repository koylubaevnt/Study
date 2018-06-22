package com.manning.gwtia.ch07.shared;

import java.util.Date;

public class BadFeedData {

	private Date createdAt;
	
	private String text;
	
	public BadFeedData(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
