package com.ebusiness.SpringBoot_Microservices_eBusiness;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootMicroservicesEBusinessApplication_suite {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMicroservicesEBusinessApplication_suite.class, args);
	}

}
