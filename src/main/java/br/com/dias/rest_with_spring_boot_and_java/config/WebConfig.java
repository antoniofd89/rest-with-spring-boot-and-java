package br.com.dias.rest_with_spring_boot_and_java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Via EXTENSION. http://localhost:8080/api/person/v1/2.xml or http://localhost:8080/api/person/v1/2.Json
        // via QUERY PARAM http://localhost:8080/api/person/v1/2?mediaType=xml

//        configurer.favorParameter(true).parameterName("mediaType")
//                .ignoreAcceptHeader(true).
//                useRegisteredExtensionsOnly(false)
//                .defaultContentType(MediaType.APPLICATION_JSON)
//                .mediaType("json",MediaType.APPLICATION_JSON)
//                .mediaType("xml",MediaType.APPLICATION_XML);

        // via Header PARAM http://localhost:8080/api/person/v1/2
           configurer.favorParameter(false)
                .ignoreAcceptHeader(false).
                useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                   .mediaType("yaml", MediaType.APPLICATION_YAML);
    }
}
