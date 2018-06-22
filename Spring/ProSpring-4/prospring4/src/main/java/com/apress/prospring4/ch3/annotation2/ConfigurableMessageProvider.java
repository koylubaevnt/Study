package com.apress.prospring4.ch3.annotation2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.prospring4.ch3.MessageProvider;

@Service("messageProvider")
public class ConfigurableMessageProvider implements MessageProvider {

	String message;

	@Autowired
	public ConfigurableMessageProvider(/*@Value("Configurable message")*/String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
