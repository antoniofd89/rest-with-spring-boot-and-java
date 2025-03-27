package br.com.dias.rest_with_spring_boot_and_java.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("REST API's RESTful from 0 with java, " +
                                "Spring boot, Kubernetes and Docker")
                        .version("V1")
                        .description("REST API's RESTful from 0 with java, Spring boot, Kubernetes and Docker")
                        .termsOfService("http://faria.dias.com.br/estudos")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://faria.dias.com.br/estudos")));
    }
}