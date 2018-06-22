package com.brysekkel.typeinfo.generics;

public class GeneratorBasic<T> implements Generator<T> {

	private Class<T> type;
	public GeneratorBasic(Class<T> type) {
		this.type = type; 		 
	}
	
	@Override
	public T next() {
		try {
			return type.newInstance();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}

	public static <T> Generator<T> create(Class<T> type) {
		return new GeneratorBasic<>(type);
	}

}
