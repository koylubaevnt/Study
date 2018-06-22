package com.apress.prospring4.ch5.l547;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.apress.prospring4.ch5.l544.SimpleBeforeAdvice;

public class ComposaЫePointcutExample {

	public static void main(String[] args) {
		SampleBean target = new SampleBean();
		
		ComposablePointcut pointcut = new ComposablePointcut(ClassFilter.TRUE, new GetterMethodМatcher());
		
		//Тест 1
		System.out.println("Test 1");
		SampleBean proxy = (SampleBean) getProxy(pointcut, target);
		testInvoke(proxy);
		
		//Тест 2
		System.out.println("Test 2");
		pointcut.union(new SetterMethodMatcher());
		proxy = (SampleBean) getProxy(pointcut, target);
		testInvoke(proxy);

		//Тест 3
		System.out.println("Test 3");
		pointcut.intersection(new GetAgeMethodMatcher());
		proxy = (SampleBean) getProxy(pointcut, target);
		testInvoke(proxy);
	}

	private static SampleBean getProxy(ComposablePointcut pointcut, SampleBean target) {
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());
		ProxyFactory factory = new ProxyFactory();
		factory.setTarget(target);
		factory.addAdvisor(advisor);
		return (SampleBean) factory.getProxy();
	}
	
	private static void testInvoke(SampleBean proxy) {
		proxy.getAge();
		proxy.getName();
		proxy.setName("Chris Shaefer");
	}
	
	private static class GetterMethodМatcher extends StaticMethodMatcher {
		
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return method.getName().startsWith("get");
		}
	}
	
	private static class GetAgeMethodMatcher extends StaticMethodMatcher {
		
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return "getAge".equals(method.getName());
		}
	}
	
	private static class SetterMethodMatcher extends StaticMethodMatcher {
		
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			return method.getName().startsWith("set");
		}
	}
}
