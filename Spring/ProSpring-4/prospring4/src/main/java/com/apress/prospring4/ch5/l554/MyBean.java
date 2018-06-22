package com.apress.prospring4.ch5.l554;

public class MyBean {

	private MyDependency dependency;
	
	public void execute() {
		dependency.foo();
		dependency.bar();
	}
	
	public void setDependency(MyDependency dependency) {
		this.dependency = dependency;
	}
	
}
