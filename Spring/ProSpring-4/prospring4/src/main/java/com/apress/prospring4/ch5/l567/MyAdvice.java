package com.apress.prospring4.ch5.l567;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class MyAdvice {

	public void simpleBeforeAdvice(JoinPoint joinPoint, int intValue) {
		if(intValue != 100) {
			System.out.println("Executing: " +
					joinPoint.getSignature().getDeclaringTypeName() + " " + 
					joinPoint.getSignature().getName() + 
					" argument: " + intValue);
		}
	}

	public Object simpleAroundAdvice(ProceedingJoinPoint proceedingJoinPoint, int intValue) throws Throwable {
		System.out.println("Before execution: " +
				proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " + 
				proceedingJoinPoint.getSignature().getName() + 
				" argument: " + intValue);
	
		Object retVal = proceedingJoinPoint.proceed();
		
		System.out.println("After execution: " +
				proceedingJoinPoint.getSignature().getDeclaringTypeName() + " " + 
				proceedingJoinPoint.getSignature().getName() + 
				" argument: " + intValue);
		
		return retVal;
	}

	
}
