package com.ho.practice.jpa.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(HibernateApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}
}
