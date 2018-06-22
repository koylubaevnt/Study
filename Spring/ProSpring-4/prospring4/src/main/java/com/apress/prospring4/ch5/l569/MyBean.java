package com.apress.prospring4.ch5.l569;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

	private MyDependency dependency;
	
	public void execute() {
		dependency.foo(100);
		dependency.foo(101);
		dependency.bar();
	}
	
	@Autowired
	public void setDependency(MyDependency dependency) {
		this.dependency = dependency;
	}
	
}
