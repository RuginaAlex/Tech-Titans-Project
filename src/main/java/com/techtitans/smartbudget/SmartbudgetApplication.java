package com.techtitans.smartbudget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SmartbudgetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartbudgetApplication.class, args);
	}

}
