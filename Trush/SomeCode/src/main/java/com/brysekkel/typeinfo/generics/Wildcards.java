package com.brysekkel.typeinfo.generics;

public class Wildcards {

	//Неспециализированный аргумент
	static void rawArgs(Holder holder, Object arg) {
		//holder.set(arg); // Предупреждение неконтролируемый вызов set...
		//holder.set(new Wildcards()); // То же предупреждение
		
		//Невозможно, T отсутсвует
		//T t = holder.get();
		
		//Возможно, но информация типа теряется
		Object obj = holder.get();
	}
	
	static void unboundedArgs(Holder<?> holder, Object arg) {
		//holder.set(arg); // Ошибка
		//holder.set(new Wildcards()); // Та же ошибка
		
		//Невозможно, T отсутсвует
		//T t = holder.get();
		
		//Возможно, но информация типа теряется
		Object obj = holder.get();
	}
	
	static <T> T exact1(Holder<T> holder) {
		T t = holder.get();
		return t;
	}
	
	static <T> T exact2(Holder<T> holder, T arg) {
		holder.set(arg);
		T t = holder.get();
		return t;
	}
	
	static <T> T wildSubtype(Holder<? extends T> holder, T arg) {
		//holder.set(arg); // Ошибка
		T t = holder.get();
		return t;
	}
	
	static <T> void wildSupertype(Holder<? super T> holder, T arg) {
		holder.set(arg); 
		//T t = holder.get(); // Ошибка
		//Возможно, но информация типа теряется
		Object obj = holder.get();
	}
	
	public static void main(String[] args) {
		Holder raw = new Holder<Long>();
		//или
		//raw = new Holder();
		Holder<Long> qualified = new Holder<Long>();
		Holder<?> unbounded = new Holder<Long>();
		Holder<? extends Long> bounded = new Holder<Long>();
		
		Long lng = 1L;
		
		rawArgs(raw, lng);
		rawArgs(qualified, lng);
		rawArgs(unbounded, lng);
		rawArgs(bounded, lng);
		
		unboundedArgs(raw, lng);
		unboundedArgs(qualified, lng);
		unboundedArgs(unbounded, lng);
		unboundedArgs(bounded, lng);

		Object r1 = exact1(raw);//Предупреждение
		Long r2 = exact1(qualified);		
		Object r3 = exact1(unbounded);
		Long r4 = exact1(bounded);
		
		Long r5 = exact2(raw, lng); // Предупреждение
		Long r6 = exact2(qualified, lng);
		//Long r7 = exact2(unbounded, lng); // Ошибка компиляции
		//Long r8 = exact2(bounded, lng); // Ошибка компиляции
		
		Long r9 = wildSubtype(raw, lng); // Предупреждение
		Long r10 = wildSubtype(qualified, lng);
		Object r11 = wildSubtype(unbounded, lng);//Возвращет только Object
		Long r12 = wildSubtype(bounded, lng); 
		
		wildSupertype(raw, lng); // Предупреждение
		wildSupertype(qualified, lng);
		//wildSupertype(unbounded, lng); // Ошибка компиляции
		//wildSupertype(bounded, lng); // Ошибка компиляции
	}

}
