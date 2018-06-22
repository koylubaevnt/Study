package com.apress.prospring4.ch5.l569;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

	@Pointcut("execution(* com.apress.prospring4.ch5.l569..foo*(int)) && args(intValue)")
	public void fooExecution(int intValue) {
		System.out.println("fooExecution");
	}
	
	@Pointcut("bean(myDependency*)")
	public void inMyDependency() {
		System.out.println("inMyDependency");
	}
	
	@Before("fooExecution(intValue) && inMyDependency()")
	public void simpleBeforeAdvice(JoinPoint joinPoint, int intValue) {
		if(intValue != 100) {
			System.out.println("Executing: " +
					joinPoint.getSignature().getDeclaringTypeName() + " " + 
					joinPoint.getSignature().getName() + 
					" argument: " + intValue);
		}
	}

	@Around("fooExecution(intValue) && inMyDependency()")
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
