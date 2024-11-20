package com.microservice.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // Aplica CORS a todos los endpoints
                        .allowedOrigins("*")  // Permite todos los orígenes
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Permite estos métodos
                        .allowedHeaders("*")  // Permite todos los encabezados
                        .allowCredentials(false);  // Si no necesitas enviar cookies o credenciales
            }
        };
    }
}
