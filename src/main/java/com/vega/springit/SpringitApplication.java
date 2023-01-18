package com.vega.springit;

import com.fasterxml.jackson.core.PrettyPrinter;
import org.ocpsoft.prettytime.PrettyTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
public class SpringitApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}


	@Bean
	PrettyTime prettyTime(){
		return new PrettyTime();
	}


	@Bean
	public SpringSecurityDialect securityDialect(){
		return new SpringSecurityDialect();
	}

}
