package com.management.database.msdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient // This annotation is used to register the service with the Eureka Server
@SpringBootApplication
public class MsDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsDatabaseApplication.class, args);
	}

}
