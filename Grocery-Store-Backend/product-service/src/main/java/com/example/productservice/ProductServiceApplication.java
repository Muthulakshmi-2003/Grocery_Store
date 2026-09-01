package com.example.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		System.setProperty("user.timezone","UTC");
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
