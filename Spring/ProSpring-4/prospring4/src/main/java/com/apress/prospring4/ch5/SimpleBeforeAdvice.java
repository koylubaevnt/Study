package com.apress.prospring4.ch5;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("Before method: " + method.getName());
	}
	
	public static void main(String[] args) {
		MessageWriter target = new MessageWriter();
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvice(new SimpleBeforeAdvice());
		
		MessageWriter proxy = (MessageWriter) factory.getProxy();
		proxy.writeMessage();
	}

}
