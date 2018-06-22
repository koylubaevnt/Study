package com.apress.prospring4.ch5.l561;

import org.aspectj.lang.JoinPoint;

public class MyAdvice {

	public void simpleBeforeAdvice(JoinPoint joinPoint){
		System.out.println("Executing: " + joinPoint.getSignature().getDeclaringTypeName() + " " +
				joinPoint.getSignature().getName());
	}

}
