package com.company.u2summative1eurekaserviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class U2Summative1EurekaServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(U2Summative1EurekaServiceRegistryApplication.class, args);
	}

}
