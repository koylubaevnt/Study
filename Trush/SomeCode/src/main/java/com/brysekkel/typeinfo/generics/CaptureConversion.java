package com.brysekkel.typeinfo.generics;

public class CaptureConversion {

	static <T> void f1(Holder<T> holder) {
		T t = holder.get();
		if(t == null || t.getClass() == null) {
			System.out.println("null");
		} else {
			System.out.println(t.getClass().getSimpleName());
		}
	}
	
	static void f2(Holder<?> holder) {
		System.out.print("f2: ");
		f1(holder);//Вызов с зафиксированным типом
	}
	
	public static void main(String[] args) {
		Holder raw = new Holder<Integer>(1); // Предупреждение
		f1(raw);// Предупреждение
		f2(raw);
		
		Holder rawBasic = new Holder(); // Предупреждение
		rawBasic.set(new Object()); // Предупреждение
		f1(rawBasic);// Предупреждение
		f2(rawBasic);
		
		Holder<?> wildcarded = new Holder<Double>(1.0);
		f1(wildcarded);
		f2(wildcarded);
	}

}
