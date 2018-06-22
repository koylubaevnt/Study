package com.apress.prospring4.ch5.l538;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import com.apress.prospring4.ch5.l522.SimpleAdvice;
import com.apress.prospring4.ch5.l526.SampleBean;
import com.apress.prospring4.ch5.l526.SimpleDynamicPointcut;
import com.apress.prospring4.ch5.l529.NameBean;

public class NameMatcherMethodPointcutUsingAdvisor {

	public static void main(String[] args) {
		NameBean target = new NameBean();
		NameBean proxy;
		
		Advice advice = new SimpleAdvice();
		//Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		NameMatchMethodPointcutAdvisor advisor = 
				new NameMatchMethodPointcutAdvisor(advice);
		advisor.addMethodName("foo");
		advisor.addMethodName("bar");
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(target);
		proxy = (NameBean) factory.getProxy();
		
		proxy.foo(100);
		proxy.foo(5);
		
		proxy.foo();
		proxy.foo();
		
		proxy.bar();
		proxy.bar();
		
		proxy.yup();
		proxy.yup();
	}
	
}
