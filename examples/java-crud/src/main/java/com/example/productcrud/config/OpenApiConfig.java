package com.example.productcrud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI productCrudOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product CRUD API")
                        .description("CRUD de produtos — exemplo Spring Boot + Docker")
                        .version("0.0.1"));
    }
}
