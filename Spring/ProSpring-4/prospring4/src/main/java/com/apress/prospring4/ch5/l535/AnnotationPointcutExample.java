package com.apress.prospring4.ch5.l535;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import com.apress.prospring4.ch5.l522.SimpleAdvice;
import com.apress.prospring4.ch5.l526.SampleBean;
import com.apress.prospring4.ch5.l526.SimpleDynamicPointcut;

public class AnnotationPointcutExample {

	public static void main(String[] args) {
		SampleAnnotationBean target = new SampleAnnotationBean();
		SampleAnnotationBean proxy;
		
		AnnotationMatchingPointcut pointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(target);
		proxy = (SampleAnnotationBean) factory.getProxy();
		
		proxy.foo1(100);
		proxy.foo1(5);
		
		proxy.foo2();
		proxy.foo2();
		
		proxy.bar();
		proxy.bar();
		
		proxy.yup();
		proxy.yup();
	}
	
}
