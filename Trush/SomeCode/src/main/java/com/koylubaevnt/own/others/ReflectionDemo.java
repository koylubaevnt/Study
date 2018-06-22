package com.koylubaevnt.own.others;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionDemo {

	public static void main(String[] args) throws Exception {
		Class clazz = Test.class;
		
		//Конструирование объекта без конструктора, если есть дефолтный\пустой конструктор
		Test test = (Test) clazz.newInstance();
		
		// Вызвать метод по имени у заданного объекта
		Method method0 = clazz.getMethod("foo");
		System.out.println(method0.toString());
		method0.invoke(test);
		
		// Установить поле по имени у заданного объекта
		Field field0 = clazz.getDeclaredField("field");
		field0.setAccessible(true);
		field0.set(test, 100);
		System.out.println(test);
		
		// Вывести название пакета
		Package pack = clazz.getPackage();
		System.out.println("package " + pack.getName() + ";");
		
		// Начинаем декларацию класса с модификаторов
		int modifiers = clazz.getModifiers();
		System.out.println(getModifiers(modifiers));
		
		// Выводим название класса
		System.out.print("class " + clazz.getSimpleName() + " ");
		// Выводим название рождительского класса
		System.out.print("extends" + clazz.getSuperclass().getSimpleName() + " ");
		
		// Выводим интерфейсы, которые реализует данный класс
		Class[] interfaces = clazz.getInterfaces();
		for(int i = 0; i < interfaces.length; i++) {
			System.out.print(i == 0 ? "implements " : ", ");
			System.out.print(interfaces[i].getSimpleName());
		}
		System.out.println(" {");
		
		
		// Выводим поля класса
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields) {
			System.out.println("\t" + getModifiers(field.getModifiers()) + 
					getType(field.getType()) + " " + field.getName() + ";");
		}
		
		// Выводим конструкторы класса
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for(Constructor constructor : constructors) {
			System.out.print("\t" + getModifiers(constructor.getModifiers()) + 
					constructor.getName() + "(");
			System.out.print(getParameters(constructor.getParameterTypes()));
			System.out.println(") { }");
		}
				
		// Выводим методы класса
		Method[] methods = clazz.getDeclaredMethods();
		for(Method method : methods) {
			// Получаем аннотации
			Annotation[] annotations = method.getAnnotations();
			System.out.print("\t");
			for(Annotation annotation : annotations) {
				System.out.print("@" + annotation.annotationType().getSimpleName() + " ");
			}
			System.out.println();
			
			System.out.print("\t" + getModifiers(method.getModifiers()) + 
					getType(method.getReturnType()) + " " + method.getName() + "(");
			System.out.print(getParameters(method.getParameterTypes()));
			System.out.println(") { }");
		}	
		
		System.out.println("}");
	}

	private static String getParameters(Class[] parameterTypes) {
		String params = "";
		for(int i = 0; i < parameterTypes.length; i++) {
			params += getType(parameterTypes[i]) + " ";
		}
		return params;
	}

	private static String getType(Class<?> type) {
		String ret = type.isArray() ? type.getComponentType().getSimpleName() 
				: type.getSimpleName();
		if(type.isArray()) {
			ret += "[]";
		}
		return ret;
	}

	private static String getModifiers(int modifiers) {
		String mod = "";
		if(Modifier.isPublic(modifiers)) {
			mod += "public ";
		}
		if(Modifier.isProtected(modifiers)) {
			mod += "protected ";
		}
		if(Modifier.isPrivate(modifiers)) {
			mod += "private  ";
		}
		if(Modifier.isStatic(modifiers)) {
			mod += "static ";
		}
		if(Modifier.isAbstract(modifiers)) {
			mod += "abstract ";
		}
		return mod;
	}
	
	
}

class Test implements Serializable, Cloneable {
	private int field;
	
	public Test() {
		
	}
	
	public Test(Object field) {
		
	}
	
	@Deprecated
	protected static void method(String[] params) {}
	
	public void foo() {
		System.out.println("FOO");
	}
	
	@Override
	public String toString() {
		return "Test{"+"field=" + field +"}";
	}
}