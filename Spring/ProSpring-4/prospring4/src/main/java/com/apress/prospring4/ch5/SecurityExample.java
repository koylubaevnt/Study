package com.apress.prospring4.ch5;

import org.springframework.aop.framework.ProxyFactory;

public class SecurityExample {

	public static void main(String[] args) {
		SecurityManager manager = new SecurityManager();
		
		SecureBean bean = getSecureBean();
		
		manager.login("chris", "pwd");
		bean.writeSecureMessage();
		manager.logout();
		
		try {
			manager.login("invaliduser", "pwd");
			bean.writeSecureMessage();
		} catch (SecurityException  e) {
			System.out.println("Exception Caught: " + e.getMessage());
		} finally {
			manager.logout();
		}
		
		try {
			bean.writeSecureMessage();
		} catch (SecurityException  e) {
			System.out.println("Exception Caught: " + e.getMessage());
		} 
	}
	
	private static SecureBean getSecureBean() {
		SecureBean target = new SecureBean();
		
		SecurityAdvice advice = new SecurityAdvice();
		
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.addAdvice(advice);
		
		SecureBean proxy = (SecureBean) factory.getProxy();
		return proxy;
	}
}
