package com.koylubaevnt.own.others;

public class InnerClassDemo {

	Inner inner = new Inner();
	int localInt = 3;
	
	interface HelloWorld {
		public void greet();
	}
	
	void anonymous() {
		HelloWorld greeting = new HelloWorld() {
			@Override
			public void greet() {
				System.out.println("Hello World from anonymous class");
				
			}
		};
		System.out.println(greeting.getClass().getName());
	}
	
	// Нельзя инстанцировать класс, если не static - если делать так new InnerClassDemo.Inner()
	// Имеет неявный конструктор с одним параметром - this
	class Inner {
		int b;
		
		// Инстанцировать и прользоваться им можно только внутри метода!
		// 
		void localMethod() {
			InnerClassDemo.this.anonymous();
			/*private */
			class Local {
				//static String field = "";
				int localInt = 0;
			}
			Local local = new Local();
			System.out.println(local.localInt);
		}
	}
	
	public static void main(String[] args) {
		InnerClassDemo.Inner inner = new InnerClassDemo().new Inner();
	}

}
