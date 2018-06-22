package ru.intuit.deepjava.firstIndependentWork.utils.reflection;

import java.lang.reflect.Field;

public class ReflectionHelper {

	public static Object createInstance(String className) {
		try {
			return Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	
	public static void setFieldValue(Object object, String fieldName, String value) {
		Field field = null;
		try {
			field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			
			if(field.getType().equals(String.class)) {
				field.set(object, value);
			} else if(field.getType().equals(int.class)) {
				field.set(object, Integer.decode(value));
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			if(field != null) {
				field.setAccessible(false);
			}
		}			
	}
}
