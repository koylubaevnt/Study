package com.apress.prospring4.ch5.l522;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		System.out.println(">> Invoking " + invocation.getMethod().getName());
		Object val = invocation.proceed();
		System.out.println(">>Done");
		return val;
	}

	
	
}
