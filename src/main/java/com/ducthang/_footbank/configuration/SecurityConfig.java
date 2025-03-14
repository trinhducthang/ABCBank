package com.ducthang._footbank.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_POST_ENDPOINTS = {
            "/auth/token",
            "auth/logout",
            "/auth/introspect",
            "api/users/create",
            "/bank/create",
            "/bank/transfer",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/loanOffer",
            "/fastLoan/**",
            "/signupLoan",
    };

    private final String[] PUBLIC_GET_ENDPOINTS ={
//            "api/users/**",
            "/api/v1/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
//            "/bank/**",
            "/bank-summary",
            "/loanOffers",
            "/loanOffer",
            "bank/getUser/**",
            "bank/info/**",
            "api/users/getname/**",
            "api/users/get-by-username/**",
            "api/users/generate-users/**",
            "/api/loan/getAll",
            "/monthly-summary/**",
            "/api/users/forgotPassword/**"
    };

    private final String[] PUBLIC_UI_ENDPOINTS ={
            "/css/loginStyle.css",
            "/login",
            "/js/loginJs.js",
            "/dashboard",
            "/transactions/**",
            "/createLoan",
            "/js/logout.js",
            "/logout",
            "/transfer",
            "/home",
            "/",
            "/signup",
            "/dangxuat",
            "/payment-failure",
            "/payment-success",
            "/dashboard/loan-offers",
            "/loanOffer/**",
            "/dashboard/transaction-details",
            "/dashboard/transaction-details/monthly-summary",
            "dashboard/account-bank",
            "/forgot-password",
            "/new_dashboard",
    };


    private final String[] PUBLIC_GET_ROLE_ADMIN ={
            "/api/users/","api/users/user-registration/**",
    };


    @Value("${jwt.signerKey}")
    private String signerKey;




    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET,PUBLIC_GET_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_UI_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, PUBLIC_GET_ROLE_ADMIN ).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/loanOffer/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/loanOffer/**","api/users/updatePassword/**").permitAll()
                        .anyRequest().authenticated()
                )
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/login")
//                        .defaultSuccessUrl("/oauth2/loginSuccess",true)
//                        .failureUrl("/oauth2/loginFailure")
//                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())
                ))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }




    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return converter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder.withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
