package com.apress.prospring4.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class AfterAdviceExample {

	private static KeyGenerator getKeyGenerator() {
		KeyGenerator target = new KeyGenerator();
		
		ProxyFactory factory = new ProxyFactory(target);
		factory.addAdvice(new WeakKeyCheckAdvice());
		
		return (KeyGenerator) factory.getProxy();
	}
	
	public static void main(String[] args) {
		KeyGenerator keyGen = getKeyGenerator();
		for(int i = 0; i < 10; i++) {
			try {
				long key = keyGen.getKey();
				System.out.println("Key: " + key);
			} catch (SecurityException e) {
				System.out.println("Weak Key Generated!");
			}
		}

	}

}
