package com.apress.prospring4.ch5.l526;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.apress.prospring4.ch5.l522.SimpleAdvice;

public class DynamicPointcutExample {

	public static void main(String[] args) {
		SampleBean target = new SampleBean();
		SampleBean proxy;
		
		Pointcut pointcut = new SimpleDynamicPointcut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(target);
		proxy = (SampleBean) factory.getProxy();
		
		proxy.foo(1);
		proxy.foo(10);
		proxy.foo(100);
		
		proxy.bar();
		proxy.bar();
		proxy.bar();
		
	}

}
