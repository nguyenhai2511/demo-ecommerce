package com.demo.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class EcommerceApplication {

	public static void main(String[] args) {
		System.out.println("API running...");
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
