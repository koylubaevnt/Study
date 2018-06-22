package com.apress.prospring4.ch5;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SimpleThrowAdvice implements ThrowsAdvice {

	public static void main(String[] args) {
		ErrorBean bean = new ErrorBean();
		
		ProxyFactory factory = new ProxyFactory(bean);
		factory.addAdvice(new SimpleThrowAdvice());
		
		ErrorBean proxy = (ErrorBean) factory.getProxy();
		
		try {
			proxy.errorPhoneMethod();
		} catch (Exception e) {

		}
		
		try {
			proxy.otherErrorPhoneMethod();
		} catch (Exception e) {

		}
	}
	
	public void afterThrowing(Exception e) throws Throwable {
		System.out.println("***");
		System.out.println("Generic Exception Capture");
		System.out.println("Caught: " + e.getClass().getName());
		System.out.println("***\n");
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, IllegalArgumentException ex)  throws Throwable {
		System.out.println("***");
		System.out.println("IllegalArgumentException Capture");
		System.out.println("Caught: " + ex.getClass().getName());
		System.out.println("Method: " + method.getName());
		System.out.println("***\n");
	}

}
