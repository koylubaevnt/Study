package com.brysekkel.typeinfo.generics;

import static com.brysekkel.typeinfo.generics.Tuple.tuple;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;


class MixinProxy implements InvocationHandler {
	Map<String, Object> delegatesByMethod;
	public MixinProxy(TupleTwo<Object, Class<?>>... pairs) {
		delegatesByMethod = new HashMap<>();
		for(TupleTwo<Object, Class<?>> pair : pairs) {
			for(Method method : pair.second.getMethods()) {
				String methodName = method.getName();
				if(!delegatesByMethod.containsKey(methodName)) {
					delegatesByMethod.put(methodName, pair.first);					
				}
			}
		}
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		Object delegate = delegatesByMethod.get(methodName);
		return method.invoke(delegate, args);
	}
	
	public static Object newInstance(TupleTwo... pairs) {
		Class[] interfaces = new Class[pairs.length];
		for(int i = 0; i < pairs.length; i++) {
			interfaces[i] = (Class)pairs[i].second;
		}
		ClassLoader cl = 
				pairs[0].first.getClass().getClassLoader();
		return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
	}
}

public class DynamicProxyMixin {

	public static void main(String[] args) {
		Object mixin = MixinProxy.newInstance(
				tuple(new BasicImpl(), Basic.class),
				tuple(new TimeStampedImpl(), TimeStamped.class),
				tuple(new SerialNumberedImpl(), SerialNumbered.class));
		Basic b = (Basic) mixin;
		TimeStamped t = (TimeStamped)mixin;
		SerialNumbered s = (SerialNumbered)mixin;
		b.set("Hello");
		System.out.println(b.get());
		System.out.println(t.getStamp());
		System.out.println(s.getSerialNumber());

	}

}
