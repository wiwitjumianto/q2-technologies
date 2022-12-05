package com.q2Technologies.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.q2Technologies.*"})
@EnableJpaRepositories({"com.q2Technologies.*"})
public class Q2TechnologiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Q2TechnologiesApplication.class, args);
	}

}
