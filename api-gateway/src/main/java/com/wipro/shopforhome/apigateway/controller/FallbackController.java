package com.wipro.shopforhome.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * To show a proper message if some service is down 
 */
@RestController
@RequestMapping("/")
public class FallbackController {

    @GetMapping("/userServiceFallback")
    public String userServiceFallback() {
        return "User service is down at this time. Please try again later.";
    }

    @GetMapping("/productServiceFallback")
    public String productServiceFallback() {
        return "Product service is down at this time. Please try again later.";
    }

    @GetMapping("/orderServiceFallback")
    public String orderServiceFallback() {
        return "Order service is down at this time. Please try again later.";
    }
    
    @GetMapping("/discountServiceFallback")
    public String discountServiceFallback() {
        return "Discount service is down at this time. Please try again later.";
    }

    @GetMapping("/configServiceFallback")
    public String configServiceFallback() {
        return "Config Server is down at this time. Please try again later.";
    }
}
