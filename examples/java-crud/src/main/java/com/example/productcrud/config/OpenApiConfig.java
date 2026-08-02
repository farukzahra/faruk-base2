package com.example.productcrud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class OpenApiConfig {

    @Bean
    OpenAPI productCrudOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Product CRUD API")
                        .description("Product catalog CRUD — Spring Boot + Docker example")
                        .version("0.0.1"));
    }
}
