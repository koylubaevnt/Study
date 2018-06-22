package com.koylubaevnt.own.others;

import java.io.Serializable;

public class Message implements Serializable {

	private Long id;
	private Long sender;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSender() {
		return sender;
	}
	public void setSender(Long sender) {
		this.sender = sender;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + "]";
	}
	
	
	
}
