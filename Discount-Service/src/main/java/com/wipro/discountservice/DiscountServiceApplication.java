package com.wipro.discountservice;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/*
 * Product Micro Service to manage all 
 * the requests related to product. 
 * 
 */
@SpringBootApplication
public class DiscountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountServiceApplication.class, args);
	}

	/*
	 * CrossOrigin configuration
	 */
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedOrigins(List.of("http://localhost:4200"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(
				List.of("Origin", "Access-Control-Allow_Origin", "authorization", "content-type", "x-auth-token"));
		configuration.setExposedHeaders(List.of("x-auth-token"));
		UrlBasedCorsConfigurationSource config = new UrlBasedCorsConfigurationSource();
		config.registerCorsConfiguration("/**", configuration);
		return new CorsFilter(config);
	}

}
