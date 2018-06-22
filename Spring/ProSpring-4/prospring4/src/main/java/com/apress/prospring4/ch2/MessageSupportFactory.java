package com.apress.prospring4.ch2;

import java.util.Properties;

public class MessageSupportFactory {

	private static MessageSupportFactory instance;
	
	private Properties properties;
	private MessageRenderer renderer;
	private MessageProvider provider;
	
	public MessageSupportFactory() {
		properties = new Properties();
		try {
			//properties.load(new FileInputStream("com/apress/prospring4/ch2/msf.properties"));//It's ot work in Eclipse
			//properties.load(new FileInputStream("target/classes/com/apress/prospring4/ch2/msf.properties"));
			properties.load(MessageSupportFactory.class.getResourceAsStream("msf.properties"));
			String renderClass = properties.getProperty("renderer.class");
			String providerClass = properties.getProperty("provider.class");
			
			renderer = (MessageRenderer) Class.forName(renderClass).newInstance();
			provider = (MessageProvider) Class.forName(providerClass).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static {
		instance = new MessageSupportFactory();
	}
	
	public static MessageSupportFactory getInstance() {
		return instance;
	}

	public MessageRenderer getMessageRenderer() {
		return renderer;
	}

	public MessageProvider getMessageProvider() {
		return provider;
	}
	
	
}
