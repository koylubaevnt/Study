package com.apress.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"META-INF/spring/services-context.xml", "META-INF/spring/repositories-context.xml"})
@SpringBootApplication
public class SpringXmlApplication {

	//TaskRepository repo;
}
