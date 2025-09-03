package com.alencar.agileboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //Desabilitando proteção CSRF para poder utilizar em ambiente de testes o postman
                .csrf(csrf -> csrf.disable())

                //Autorizando requisições http, ambiente de desenvolvimento
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                //Mantenho a pagina de login e senha padrão do JPA
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }
}
