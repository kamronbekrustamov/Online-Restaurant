package com.webproject.restaurant.config;

import com.webproject.restaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Setting The Authentication to BASIC
        http.httpBasic();

        // Configuring CORS
        http.cors().configurationSource(httpServletRequest -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("*"));
            configuration.setAllowedMethods(List.of("*"));
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setAllowCredentials(true);
            configuration.setMaxAge(3600L);
            return configuration;
        });

        // Disabling CSRF
        http.csrf().disable();

        // Securing http://localhost:8080/users/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/users/*").anonymous()
                .mvcMatchers(HttpMethod.POST, "/users/authenticated/*").authenticated();

        // Securing http://localhost:8080/reviews/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/reviews/*").permitAll()
                .mvcMatchers(HttpMethod.POST, "/reviews/*").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE, "/reviews/*").hasRole("ADMIN");

        // Securing http://localhost:8080/foods/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/foods/*").permitAll()
                .mvcMatchers(HttpMethod.POST, "/foods/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/foods/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/foods/*").hasRole("ADMIN");

        // Securing http://localhost:8080/bookings/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/bookings/*").authenticated()
                .mvcMatchers(HttpMethod.POST, "/bookings/*").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT, "/bookings/*").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/bookings/*").authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }
}
