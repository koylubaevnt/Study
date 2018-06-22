package com.apress.prospring4.ch5.l535;

public class SampleAnnotationBean {

	@AdviceRequired
	public void foo1(int x) {
		System.out.println("foo1: " + x);
	}
	
	public void foo2() {
		System.out.println("foo2");
	}
	
	public void bar() {
		System.out.println("bar");
	}
	
	@AdviceRequired
	public void yup() {
		System.out.println("yup");
	}
	
}
