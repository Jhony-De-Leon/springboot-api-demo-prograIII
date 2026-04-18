package com.ejemplo.demo.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        		
                .info(new Info()
                        .title("API Workshop Spring Boot")
                        .version("1.0")
                        .description("API REST para gestión de saludos y simulación de préstamos con validaciones y manejo de errores")
                        .contact(new Contact()
                                .name("Jhony De León")
                                .email("jdeleonp29@miumg.edu.gt")
                        )
                )

                .externalDocs(new ExternalDocumentation()
                        .description("Documentación oficial Springdoc")
                        .url("https://springdoc.org"))

                .addTagsItem(new Tag()
                        .name("Saludos")
                        .description("Operaciones relacionadas con saludos"))

                .addTagsItem(new Tag()
                        .name("Préstamos")
                        .description("Operaciones de simulación de préstamos"));
    }
}