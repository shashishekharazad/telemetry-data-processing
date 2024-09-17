package com.dell.corpaggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://10.227.229.235:8080", "http://localhost:8080", "http://127.0.0.1:8087","https://127.0.0.1:3000") // List of allowed origins
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD");
            }
        };
    }
}
