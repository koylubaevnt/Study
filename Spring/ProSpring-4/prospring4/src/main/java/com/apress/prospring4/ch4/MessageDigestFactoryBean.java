package com.apress.prospring4.ch4;

import java.security.MessageDigest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {

	private String alghoritmName = "MD5";
	
	private MessageDigest messageDigestFactoryBean = null;
	
	@Override
	public MessageDigest getObject() throws Exception {
		return messageDigestFactoryBean;
	}
	
	@Override
	public Class<?> getObjectType() {
		return MessageDigest.class;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		messageDigestFactoryBean = MessageDigest.getInstance(alghoritmName);
	}

	public void setAlghoritmName(String alghoritmName) {
		this.alghoritmName = alghoritmName;
	}
}
