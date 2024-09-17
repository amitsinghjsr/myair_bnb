package com.airbnb.air_bnb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.airbnb.air_bnb.repositories")

public class AirBnbApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirBnbApplication.class, args);
	}

}
