package com.apress.prospring4.ch5;

public class UserInfo {

	private String userName;
	private String password;
	
	public UserInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
}
