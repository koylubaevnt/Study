package com.brysekkel.typeinfo.generics;

public class Holder<T> {

	private T value;
	public Holder() { }
	public Holder(T value) {
		this.value = value;
	}
	public void set(T value) {
		this.value = value;
	}
	public T get() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return value.equals(obj);
	}
	
	public static void main(String[] args) {
		Holder<Apple> apple = new Holder<Apple>(new Apple());
		Apple d = apple.get();
		apple.set(d);
		//Не может выполнить восходящее преобразование
		//Holder<Fruit> Fruit = Apple;
		Holder<? extends Fruit> fruit = apple; //ОК
		Fruit p =  fruit.get();
		d = (Apple)fruit.get();//Возвращает Object
		try {
			Orange с = (Orange)fruit.get();//без предупреждений
		} catch (Exception e) {
			System.out.println(e);
		}
		//fruit.set(new Apple());//невозможно из-за Wildcards ? extends
		//fruit.set(new Fruit());//невозможно из-за Wildcards ? extends
		System.out.println(fruit.equals(d));//ОК - из-за параметра Object!
		
	}
}

