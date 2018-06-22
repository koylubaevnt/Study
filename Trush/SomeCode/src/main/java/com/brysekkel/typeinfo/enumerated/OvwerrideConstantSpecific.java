package com.brysekkel.typeinfo.enumerated;

public enum OvwerrideConstantSpecific {
	NUT, BOLT,
	WASHER {
		@Override
		void f() { System.out.println("Overriden method"); }
	};
	void f() { System.out.println("default behavior"); }
	
	public static void main(String[] args) {
		for(OvwerrideConstantSpecific ocs : values()) {
			System.out.print(ocs + ": ");
			ocs.f();
		}
	}
}
