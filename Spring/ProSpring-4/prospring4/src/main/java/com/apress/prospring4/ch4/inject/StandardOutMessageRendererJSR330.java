package com.apress.prospring4.ch4.inject;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.apress.prospring4.ch4.MessageProvider;
import com.apress.prospring4.ch4.MessageRenderer;

@Named("messageRenderer")
@Singleton
public class StandardOutMessageRendererJSR330 implements MessageRenderer {

	@Inject
	@Named("messageProvider")
	private MessageProvider provider;
	
	@Override
	public void render() {
		if(provider == null) {
			throw new RuntimeException("You must set the property provider of class:" + 
					StandardOutMessageRendererJSR330.class.getName());
		}
		System.out.println(provider.getMessage());
	}

	@Override
	public void setMessageProvider(MessageProvider provider) {
		this.provider = provider;
	}

	@Override
	public MessageProvider getMessageProvider() {
		return provider;
	}

}
