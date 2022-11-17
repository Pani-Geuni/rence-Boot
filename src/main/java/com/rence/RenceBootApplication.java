package com.rence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.rence.dashboard.model"},enableDefaultTransactions = false)
public class RenceBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(RenceBootApplication.class, args);
		
	}
	
}
