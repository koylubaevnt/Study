package com.apress.prospring4.ch8.l837;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareBean implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "prospring4";
	}

}
