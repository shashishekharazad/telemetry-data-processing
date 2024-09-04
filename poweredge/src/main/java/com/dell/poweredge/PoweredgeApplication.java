package com.dell.poweredge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PoweredgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoweredgeApplication.class, args);
	}

}
