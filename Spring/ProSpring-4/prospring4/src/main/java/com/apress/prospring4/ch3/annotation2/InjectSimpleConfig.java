package com.apress.prospring4.ch3.annotation2;

import org.springframework.stereotype.Component;

@Component("injectSimpleConfig")
public class InjectSimpleConfig {

	public String name = "chris Schaefer";
	public int age = 32;
	public float height = 1.778f;
	public boolean programmer = true;
	public Long ageInSeconds = 1009843200L;
	
	
}
