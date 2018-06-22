package com.apress.prospring4.ch5.l549;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

	public IsModifiedAdvisor() {
		super(new IsModifiedМixin());
	}
}
