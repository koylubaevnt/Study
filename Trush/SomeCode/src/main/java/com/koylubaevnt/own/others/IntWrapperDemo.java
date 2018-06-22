package com.koylubaevnt.own.others;

public class IntWrapperDemo {

	interface Monad<T> {
		
		T apply(T t);
		
	}
	
	static class IntWrapper {
		Monad<Integer> num;
		Monad<String> operation;
		
		public IntWrapper setNum(Monad<Integer> num) {
			this.num = num;
			return this;
		}
		
		public IntWrapper setOperation(Monad<String> operation) {
			this.operation = operation;
			return this;
		}
		
		public int calculate(int num2) {
			int result = 0;
			operation.apply("+");
			num.apply(12);
//			switch (operation) {
//			case "+":
//				result = this.num + num;
//				break;
//			case "-":
//				result = this.num - num;
//				break;
//			}
			return result;
		}
	}
	
	public static void main(String[] args) {
		IntWrapper intWrapper = new IntWrapper();
		intWrapper
		.setOperation(new Monad<String>() {

			@Override
			public String apply(String t) {
				System.out.println("apply String");
				return t.substring(1);
			}
		}).setNum(new Monad<Integer>() {

			@Override
			public Integer apply(Integer t) {
				System.out.println("apply Integer");
				return t + 1;
			}
		}).calculate(12);
//			.setOperation("+")
//			.setNum(2)
//			.calculate(3);

	}

}
