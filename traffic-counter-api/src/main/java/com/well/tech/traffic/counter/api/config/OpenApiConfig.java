package com.well.tech.traffic.counter.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI trafficCounterOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Traffic Counter API")
                        .description("REST API for traffic monitoring devices, maintenance and status history.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Well Tech")));
    }
}