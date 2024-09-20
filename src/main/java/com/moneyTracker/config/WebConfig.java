package com.moneyTracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200","https://money-tracker-frontend-65ub.onrender.com")  // Replace with your Angular frontend URL
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")  // Allow specific methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true)  // Allow credentials (cookies, authorization headers)
                .maxAge(3600);  // Cache the response for 1 hour
    }
}
