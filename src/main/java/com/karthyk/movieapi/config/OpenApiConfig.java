package com.karthyk.movieapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI movieApi() {
        return new OpenAPI().info(new Info()
                .title("Movie API")
                .description("CRUD with Kafka events")
                .version("v1"));
    }
}
