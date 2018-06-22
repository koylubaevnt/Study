package com.apress.prospring4.ch2;

public class StandardOutMessageRenderer implements MessageRenderer {

	private MessageProvider provider;
	
	public void render() {
		if(provider == null) {
			throw new RuntimeException("You must set the property provider of class:" + 
					StandardOutMessageRenderer.class.getName());
		}
		System.out.println(provider.getMessage());
	}

	public void setMessageProvider(MessageProvider provider) {
		this.provider = provider;
	}

	public MessageProvider getMessageProvider() {
		return provider;
	}

}
