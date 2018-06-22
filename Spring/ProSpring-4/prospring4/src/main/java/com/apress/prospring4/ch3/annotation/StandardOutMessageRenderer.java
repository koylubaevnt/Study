package com.apress.prospring4.ch3.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apress.prospring4.ch3.MessageProvider;
import com.apress.prospring4.ch3.MessageRenderer;

@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider provider;
	
	@Override
	public void render() {
		if(provider == null) {
			throw new RuntimeException("You must set the property provider of class:"
					+ StandardOutMessageRenderer.class.getName());
		}
		System.out.println(provider.getMessage());
	}

	@Override
	@Autowired
	public void setMessageProvider(MessageProvider provider) {
		this.provider = provider;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return this.provider;
	}

}
