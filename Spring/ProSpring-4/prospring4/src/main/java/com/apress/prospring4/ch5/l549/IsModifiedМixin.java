package com.apress.prospring4.ch5.l549;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

public class IsModifiedМixin extends DelegatingIntroductionInterceptor implements IsModified {

	private boolean isModified = false;
	private Map<Method, Method> methodCache = new HashMap<>();
	
	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		if(!isModified) {
			if((mi.getMethod().getName().startsWith("set"))
					&&mi.getArguments().length == 1) {
				Method getter = getGetter(mi.getMethod());
				
				if(getter != null) {
					Object newVal = mi.getArguments()[0];
					Object oldVal = getter.invoke(mi.getThis(), null);
					if(newVal == null && oldVal == null) {
						isModified = false;
					} else if(newVal == null && oldVal != null) {
						isModified = true;
					} else if(newVal != null && oldVal == null) {
						isModified = true;
					} else {
						isModified = (!newVal.equals(oldVal));
					}
				}
			}
		}
		return super.invoke(mi);
	}
	
	private Method getGetter(Method setter) {
		Method getter = null;
		getter = (Method) methodCache.get(setter);
		
		if(getter != null) {
			return getter;
		}
		
		String getterName = setter.getName().replaceFirst("set", "get");
		try {
			getter = setter.getDeclaringClass().getMethod(getterName, null);
			
			synchronized (methodCache) {
				methodCache.put(setter, getter);
			}
			return getter;
		} catch (NoSuchMethodException e) {
			return null;
		}
	}
}
