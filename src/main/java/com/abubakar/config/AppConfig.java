package com.abubakar.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
public class AppConfig {


@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        .cors(cors -> 
            cors.configurationSource(corsConfigurationSource())
        )

        .sessionManagement(sm ->
            sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )

        .authorizeHttpRequests(auth -> auth


            // auth
            .requestMatchers("/api/auth/**")
            .permitAll()


            // products public
            .requestMatchers("/api/products/**")
            .permitAll()


            // razorpay public
            .requestMatchers("/api/create-order")
            .permitAll()

            .requestMatchers("/api/verify-payment")
            .permitAll()


            // OPTIONS
            .requestMatchers(
                HttpMethod.OPTIONS,
                "/**"
            )
            .permitAll()

            .requestMatchers("/home/**").permitAll()
            .anyRequest()
            .permitAll()
        )


        .addFilterBefore(
            new JwtTokenValidator(),
            org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class
        );


    return http.build();
}



private CorsConfigurationSource corsConfigurationSource() {

    return request -> {

        CorsConfiguration cfg =
            new CorsConfiguration();

        cfg.setAllowedOrigins(
            Arrays.asList("*")
        );

        cfg.setAllowedMethods(
            Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
            )
        );

        cfg.setAllowedHeaders(
            Arrays.asList("*")
        );

        cfg.setExposedHeaders(
            Arrays.asList("Authorization")
        );

        cfg.setAllowCredentials(false);

        return cfg;
    };
}



@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}



@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}

}