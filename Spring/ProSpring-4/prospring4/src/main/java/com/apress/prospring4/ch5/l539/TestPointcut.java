package com.apress.prospring4.ch5.l539;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class TestPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		return "advised".equals(method.getName());
	}

}
