package com.apress.prospring4.ch5.l561;

public class MyBean {

	private MyDependency dependency;
	
	public void execute() {
		dependency.foo(100);
		dependency.foo(101);
		dependency.bar();
	}
	
	public void setDependency(MyDependency dependency) {
		this.dependency = dependency;
	}
	
}
