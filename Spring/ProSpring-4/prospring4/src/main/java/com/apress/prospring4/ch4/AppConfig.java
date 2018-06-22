package com.apress.prospring4.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ImportResource(value="classpath:META-INF/spring/ch4/app-context-xml-4.16.xml")
@PropertySource(value="classpath:message.properties")
@ComponentScan(basePackages= {"com.apress.prospring.ch4"})
@EnableTransactionManagement
public class AppConfig {

	@Autowired
	Environment environment;
	
	@Bean
	@Lazy(value=true)
	public MessageProvider messageProvider() {
		return new ConfigurableMessageProvider(environment.getProperty("message"));
	}

	@Bean(name="messageRenderer")
	@Scope(value="prototype")
	@DependsOn(value="messageProvider")
	public MessageRenderer messageRenderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(messageProvider());
		
		return renderer;
	}
	
}
