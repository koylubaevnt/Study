package com.brysekkel.typeinfo.enumerated;

enum LikeClasses {
	WINKEN { 
		@Override
		void behavior() { System.out.println("Behaviour1"); } 
		},
	BLINKEN { 
		@Override
		void behavior() { System.out.println("Behaviour2"); } 
		},
	NOD { 
		@Override
		void behavior() { System.out.println("Behaviour3"); } 
		};
	abstract void behavior();
}

public class NotClasses {

	//void f1(LikeClasses.WINKEN instanse) {} // Нельзя
	
	public static void main(String[] args) {
		

	}

}
