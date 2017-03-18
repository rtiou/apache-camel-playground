package com.tiou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApacheCamelPlaygroundApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApacheCamelPlaygroundApplication.class, args);
	}
}
