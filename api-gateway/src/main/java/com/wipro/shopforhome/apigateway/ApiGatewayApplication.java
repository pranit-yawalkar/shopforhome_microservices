package com.wipro.shopforhome.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/*
 * Api Gateway Application to integrate all the 
 * micro-services and to access all the micro-services 
 * using a common hostname and port
 */
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
