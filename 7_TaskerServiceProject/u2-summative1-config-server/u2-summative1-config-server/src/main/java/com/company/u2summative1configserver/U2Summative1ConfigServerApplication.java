package com.company.u2summative1configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class U2Summative1ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(U2Summative1ConfigServerApplication.class, args);
	}

}
