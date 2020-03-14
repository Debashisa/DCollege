package com.deba.college.dcollege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class DcollegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DcollegeApplication.class, args);
	}

}
