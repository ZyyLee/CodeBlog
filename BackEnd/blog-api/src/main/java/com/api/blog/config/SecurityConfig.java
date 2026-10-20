package com.api.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration 
public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http
    //Regras serão adicionadas aqui
    .authorizeHttpRequests(auth -> auth
        // Get publico
        .requestMatchers(HttpMethod.GET, 
            "/api/posts",
            "/api/posts/**",
            "/api/comentarios",
            "/api/comentarios/**"
        ).permitAll()
        //Post publico
        .requestMatchers(HttpMethod.POST,
            "/api/newpost"
        ).permitAll()

        //Patch Admin
        .requestMatchers(HttpMethod.PATCH,
             "/api/posts/**",
             "/api/comentarios/**"
            ).hasRole("ADMIN")
        //Delete admin
        .requestMatchers(HttpMethod.DELETE,
            "/api/posts/**",
            "/api/comentarios/**"
        ).hasRole("ADMIN")
        //Qualquer outra rota exige autenticação
        
    .anyRequest().authenticated()
    )

    //Integra a configuração CORS com o spring security
    .cors(cors -> {})

    //Desabilitado para facilitar os testes da API
    .csrf(csrf -> csrf.disable())

    //Mnatém a autenticação basic
    .httpBasic(Customizer.withDefaults());

    return http.build();
}

@Bean 
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
