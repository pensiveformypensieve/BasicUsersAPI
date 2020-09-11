package com.horcrux.ravenclaw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
//		(exclude = {SecurityAutoConfiguration.class})
public class RavenclawApplication {

	final static Logger log = LoggerFactory.getLogger(RavenclawApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(RavenclawApplication.class, args);

		log.debug("app successfully ran");
	}

}
