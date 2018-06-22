package com.apress.prospring4.ch5;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class ProfilingInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(invocation.getMethod().getName());
		
		Object val = invocation.proceed();
		
		stopWatch.stop();
		
		dumpInfo(invocation, stopWatch.getTotalTimeMillis());
		
		return val;
	}

	private void dumpInfo(MethodInvocation invocation, long ms) {
		Method method = invocation.getMethod();
		Object target = invocation.getThis();
		Object[] args = invocation.getArguments();
		
		System.out.println("Executed method: " + method.getName());
		
		System.out.println("On object of type: " + target.getClass().getName());
		
		System.out.println("With arguments:");
		for(int i = 0; i < args.length; i++) {
			System.out.print("	> " + args[i]);
		}
		System.out.println("\n");
		System.out.println("Took: " + ms + " ms");
	}
	
}
