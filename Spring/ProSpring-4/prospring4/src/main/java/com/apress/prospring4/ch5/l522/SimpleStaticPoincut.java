package com.apress.prospring4.ch5.l522;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimpleStaticPoincut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		System.out.println("Static check for " + method.getName());
		return "foo".equals(method.getName());
	}

	@Override
	public ClassFilter getClassFilter() {
		System.out.println("Calling getClassFilter");
		return new ClassFilter() {
			
			@Override
			public boolean matches(Class<?> clazz) {
				System.out.println("Calling getClassFilter.matches for " + clazz.getName());
				return clazz == BeanOne.class;
			}
		};
	}
	
}
