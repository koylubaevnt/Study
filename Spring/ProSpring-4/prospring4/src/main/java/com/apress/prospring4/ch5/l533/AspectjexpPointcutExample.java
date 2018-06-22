package com.apress.prospring4.ch5.l533;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;

import com.apress.prospring4.ch5.l522.SimpleAdvice;
import com.apress.prospring4.ch5.l526.SampleBean;
import com.apress.prospring4.ch5.l526.SimpleDynamicPointcut;

public class AspectjexpPointcutExample {

	public static void main(String[] args) {
		AspectjexpBean target = new AspectjexpBean();
		AspectjexpBean proxy;
		
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* foo*(..))");
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
		
		ProxyFactory factory = new ProxyFactory();
		factory.addAdvisor(advisor);
		factory.setTarget(target);
		proxy = (AspectjexpBean) factory.getProxy();
		
		proxy.foo1();
		proxy.foo1();
		
		proxy.foo2();
		proxy.foo2();
		
		proxy.bar();
		proxy.bar();
		
		proxy.yup();
		proxy.yup();
	}
	
}
