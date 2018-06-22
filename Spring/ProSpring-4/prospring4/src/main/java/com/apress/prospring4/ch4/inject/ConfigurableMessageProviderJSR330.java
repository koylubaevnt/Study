package com.apress.prospring4.ch4.inject;

import javax.inject.Inject;
import javax.inject.Named;

import com.apress.prospring4.ch4.MessageProvider;

@Named("messageProvider")
public class ConfigurableMessageProviderJSR330 implements MessageProvider {

	private String message = "message";

	public ConfigurableMessageProviderJSR330() {
	
	}
	
	@Inject
	@Named("message")
	public ConfigurableMessageProviderJSR330(String message) {
		this.message = message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
