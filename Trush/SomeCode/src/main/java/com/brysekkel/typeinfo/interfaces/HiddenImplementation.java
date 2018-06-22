package com.brysekkel.typeinfo.interfaces;

import java.lang.reflect.Method;

import com.brysekkel.typeinfo.packageaccess.HiddenC;

public class HiddenImplementation {

	public static void main(String[] args) throws Exception {
		A a = HiddenC.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		/*//Не удается найти имя "C"
		if (a instanceof C) {
			C с = (С) a;
			c.g;
		}
		*/
		//Reflection зволяет вызывать g и даже менее доступные методы
		callHiddenMethod(a, "g");
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "v");
		callHiddenMethod(a, "w");
	}
	
	
	static void callHiddenMethod(Object a, String methodName) throws Exception {
		Method g = a.getClass().getDeclaredMethod(methodName);
		g.setAccessible(true);
		g.invoke(a);
	}
}
