package com.apress.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class SpringBootSimpleApplication implements CommandLineRunner, ApplicationRunner {

	private static Logger log = LoggerFactory.getLogger(SpringBootSimpleApplication.class);	
	
	public static void main(String[] args) {
		
//		SpringApplication app = new SpringApplication(SpringBootSimpleApplication.class);
//		app.setBanner(new Banner() {
//			
//			@Override
//			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
//				out.print("\n\n\tThis is my own banner!\n\n".toUpperCase());
//				
//			}
//		});
//		app.setBannerMode(Mode.CONSOLE);
//		app.run(args);
		
//		
//		new SpringApplicationBuilder()
//			.listeners(new ApplicationListener<ApplicationEvent>() {
//
//				@Override
//				public void onApplicationEvent(ApplicationEvent event) {
//					log.info("#### > " + event.getClass().getCanonicalName());
//				}
//				
//			})
//			//.bannerMode(Mode.OFF)
//			.sources(SpringBootSimpleApplication.class)
//			//.profiles("prod", "cloud")
//			//.logStartupInfo(false)
//			.run(args);
	
		SpringApplication.run(SpringBootSimpleApplication.class, args);
	}
	
	@Bean
	String info() {
		return "Just a simple String bean";
	}
	
	@Autowired
	String info;

	@Autowired
	private MyService service;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("## > ApplicationRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		args.getNonOptionArgs().forEach(file -> log.info(file));
		log.info("## > server=" + service);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("## > CommandLineRunner Implementation...");
		log.info("Accessing the Info bean: " + info);
		for(String arg : args) {
			log.info(arg);
		}
		log.info("## > server=" + service);		
	}
	
	
}

@Component
class MyComponent {
	private static final Logger log = LoggerFactory.getLogger(MyComponent.class);
	
	@Autowired
	public MyComponent(ApplicationArguments args) {
		boolean enable = args.containsOption("enable");
		if(enable)
			log.info("## > You are enable!");
		List<String> _args = args.getNonOptionArgs();
		log.info("## > extra args ...");
		if(!_args.isEmpty()) {
			_args.forEach(file -> log.info(file));
		}
	}
}

@Service
class MyService {
	
	private static final Logger log = LoggerFactory.getLogger(MyService.class);
	
	@Value("${data.server}")
	private String server;

	public MyService() {
		log.info("## > Constructor");
	}
	
	@Override
	public String toString() {
		return "MyService [server=" + server + "]";
	}
	
	
}
