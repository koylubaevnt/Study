package com.apress.prospring4.ch3;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class FormatMessageReplacer implements MethodReplacer {

	@Override
	public Object reimplement(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		if(isFormatMessageMethod(arg1)) {
			String msg = (String) arg2[0];
			return "<h2>" + msg + "</h2>";
		} else {
			throw new IllegalArgumentException("Unable to reimplement method " + arg1.getName());
		}
	}

	private boolean isFormatMessageMethod(Method method) {
		if(method.getParameterTypes().length != 1) {
			return false;
		}
		
		if(!("formatMessage".equals(method.getName()))) {
			return false;
		}
		
		if(method.getReturnType() != String.class) {
			return false;
		}
		
		if(method.getParameterTypes()[0] != String.class) {
			return false;
		}
		
		return true;
	}
	
}
