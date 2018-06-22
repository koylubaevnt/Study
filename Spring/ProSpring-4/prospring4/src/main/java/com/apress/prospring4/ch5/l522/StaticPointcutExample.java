package com.apress.prospring4.ch5.l522;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class StaticPointcutExample {

	public static void main(String[] args) {
		BeanOne one = new BeanOne();
		BeanTwo two = new BeanTwo();
		
		BeanOne proxyOne;
		BeanTwo proxyTwo;
		
		Pointcut pointcut = new SimpleStaticPoincut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(one);
		proxyOne = (BeanOne) factory.getProxy();
		
		factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(two);
		proxyTwo = (BeanTwo) factory.getProxy();
		
		proxyOne.foo();
		proxyTwo.foo();
		
		proxyOne.bar();
		proxyTwo.bar();
		
		proxyOne.foo();
		proxyTwo.foo();
		
		proxyOne.bar();
		proxyTwo.bar();
		
		proxyOne.foo();
		proxyTwo.foo();
		
		proxyOne.bar();
		proxyTwo.bar();
	}

}
