package com.sprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RestaurantProjectApplication {
	
	@GetMapping("/message")
	public String getMessage()
	{
		return "Welcome to RestaurantBooking system";
	}

	public static void main(String[] args) {
		SpringApplication.run(RestaurantProjectApplication.class, args);
	}

}
