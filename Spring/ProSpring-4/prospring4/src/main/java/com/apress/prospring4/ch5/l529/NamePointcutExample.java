package com.apress.prospring4.ch5.l529;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import com.apress.prospring4.ch5.l522.SimpleAdvice;
import com.apress.prospring4.ch5.l526.SampleBean;
import com.apress.prospring4.ch5.l526.SimpleDynamicPointcut;

public class NamePointcutExample {

	public static void main(String[] args) {
		NameBean target = new NameBean();
		NameBean proxy;
		
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		pointcut.addMethodName("foo");
		pointcut.addMethodName("bar");
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(target);
		proxy = (NameBean) factory.getProxy();
		
		proxy.foo();
		proxy.foo();
		proxy.foo(999);
		proxy.foo(100);
		
		proxy.bar();
		proxy.bar();
		proxy.yup();
		proxy.yup();
	}
	
}
