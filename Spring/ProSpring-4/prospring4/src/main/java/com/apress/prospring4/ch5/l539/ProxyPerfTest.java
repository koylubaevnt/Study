package com.apress.prospring4.ch5.l539;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class ProxyPerfTest {

	public static void main(String[] args) {
		SimpleBean target = new DefaultSimpleBean();
		
		Advisor advisor = new DefaultPointcutAdvisor(new TestPointcut(), new NoOpBeforeAdvice());
		runCglibTests(advisor, target);
		runCglibFrozenTests(advisor, target);
		runJdkTests(advisor, target);
		
	}

	private static void runCglibTests(Advisor advisor, SimpleBean target) {
		ProxyFactory factory = new ProxyFactory();
		factory.setProxyTargetClass(true);
		factory.setTarget(target);
		factory.addAdvisor(advisor);
		
		SimpleBean proxy = (SimpleBean) factory.getProxy();
		System.out.println("Running CGLIB (Standard) Tests");
		test(proxy);
	}
	
	private static void runCglibFrozenTests(Advisor advisor, SimpleBean target) {
		ProxyFactory factory = new ProxyFactory();
		factory.setProxyTargetClass(true);
		factory.setTarget(target);
		factory.addAdvisor(advisor);
		factory.setFrozen(true);
		
		SimpleBean proxy = (SimpleBean) factory.getProxy();
		System.out.println("Running CGLIB (Frozen) Tests");
		test(proxy);
	}
	
	private static void runJdkTests(Advisor advisor, SimpleBean target) {
		ProxyFactory factory = new ProxyFactory();
		factory.setInterfaces(new Class[] {SimpleBean.class});
		factory.setTarget(target);
		factory.addAdvisor(advisor);
		
		SimpleBean proxy = (SimpleBean) factory.getProxy();
		System.out.println("Running JDK Tests");
		test(proxy);
	}

	private static void test(SimpleBean bean) {
		long before = 0;
		long after = 0;
		
		// Тестирование метода, снабженного советом 
		System.out.println("Testing Advised Method");
		before = System.currentTimeMillis();
		for(int i = 0; i < 500_000; i++) {
			bean.advised();
		}
		after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms");
		
		// Тестирование метода, не снабженного советом 
		System.out.println("Testing Unadvised Method");
		before = System.currentTimeMillis();
		for(int i = 0; i < 500_000; i++) {
			bean.unadvised();
		}
		after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms");
		
		// Тестирование метода equals 
		System.out.println("Testing equals() Method");
		before = System.currentTimeMillis();
		for(int i = 0; i < 500_000; i++) {
			bean.equals(bean);
		}
		after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms");
		
		// Тестирование метода hashCode 
		System.out.println("Testing hashCode() Method");
		before = System.currentTimeMillis();
		for(int i = 0; i < 500_000; i++) {
			bean.hashCode();
		}
		after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms");
		
		Advised advised = (Advised) bean;
		System.out.println("Testing Advised.getProxyTargetClass");
		before = System.currentTimeMillis();
		for(int i = 0; i < 500_000; i++) {
			advised.getTargetClass();
		}
		after = System.currentTimeMillis();
		System.out.println("Took " + (after - before) + " ms");
		System.out.println(">>>\n");
		
	}
}
