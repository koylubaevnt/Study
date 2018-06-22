package com.manning.gwtia.ch07.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class FeedData implements IsSerializable {

	private Date createdAt;
	
	private String text;
	
	public FeedData() {
		/*
		 * Нужен для GWT - авто генерация сериализациим для этого класса
		 */
	}
	
	public FeedData(Date createdAt, String text) {
		this.createdAt = createdAt;
		this.text = text;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public String getText() {
		return text;
	}
}
