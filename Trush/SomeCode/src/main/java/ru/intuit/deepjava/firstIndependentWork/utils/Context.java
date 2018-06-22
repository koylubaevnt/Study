package ru.intuit.deepjava.firstIndependentWork.utils;

import java.util.HashMap;
import java.util.Map;

public class Context {

	private Map<Class<?>, Object> context = new HashMap<>();
	
	public void add(Class<?> clazz, Object object) throws Exception {
		if(context.containsKey(clazz)) {
			throw new Exception();
		}
		context.put(clazz, object);
	}

	public Object get(Class<?> clazz) {
		return context.get(clazz);
	}
}
