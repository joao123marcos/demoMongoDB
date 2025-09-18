package com.JoaoMarcos.demoMongoDB.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityApp {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) 
      throws Exception {

        return httpSecurity
                // 1. Desabilita a proteção CSRF por ser uma API stateless 
                //(sem sessão)
                .csrf(csrf -> csrf.disable())
                
                // 2. Configura a política de gerenciamento de sessão para 
                // STATELESS
                // O servidor não criará nem manterá nenhuma sessão HTTP.
                .sessionManagement(session -> session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS))
                
                // 3. Configura as regras de autorização para as requisições HTTP
                .authorizeHttpRequests(authorize -> authorize
                        
                        //4. Regra: Para requisições POST para "/users", o 
                        //usuário deve ter a permissão "ADMIN"
                        .requestMatchers(HttpMethod.POST, 
                         "/auth/login").permitAll()

                        .requestMatchers(HttpMethod.POST, 
                         "/auth/register").permitAll()

                        .requestMatchers(HttpMethod.POST, "/users")
                        .hasAuthority("ADMIN")
                        
                        // 5. Regra: Qualquer outra requisição (anyRequest) 
                        //precisa estar autenticada
                        .anyRequest().authenticated()
                )
                
                // 6. Constrói o objeto SecurityFilterChain
                .build();       
    }

    @Bean
    public AuthenticationManager authenticationManager 
     (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}