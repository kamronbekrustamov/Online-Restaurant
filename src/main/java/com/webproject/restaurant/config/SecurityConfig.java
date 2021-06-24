package com.webproject.restaurant.config;

import com.webproject.restaurant.filter.JwtTokenGeneratorFilter;
import com.webproject.restaurant.filter.JwtTokenValidatorFilter;
import com.webproject.restaurant.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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
        // HTTP BASIC Authentication
        http.httpBasic();

        // Disabling Session Management
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Adding JWT Filters
        http.addFilterBefore(new JwtTokenValidatorFilter(userService), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class);

        // Disabling CSRF
        http.csrf().disable();

        // Configuring CORS
        http.cors().configurationSource(httpServletRequest -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("http://localhost:3000"));
            configuration.setAllowedMethods(List.of("*"));
            configuration.setAllowCredentials(true);
            configuration.setAllowedHeaders(List.of("*"));
            configuration.setExposedHeaders(List.of("Authorization"));
            configuration.setMaxAge(3600L);
            return configuration;
        });

        // Securing http://localhost:8080/users/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/api/v1/users/authenticate").authenticated()
                .mvcMatchers(HttpMethod.POST, "/api/v1/users/register").anonymous();

        // Securing http://localhost:8080/reviews/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/v1/reviews").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/v1/reviews").hasRole("USER")
                .mvcMatchers(HttpMethod.DELETE, "/api/v1/reviews/*").hasRole("ADMIN");

        // Securing http://localhost:8080/foods/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/v1/foods/*").permitAll()
                .mvcMatchers(HttpMethod.POST, "/api/v1/foods").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/api/v1/foods/*").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/api/v1/foods/*").hasRole("ADMIN");

        // Securing http://localhost:8080/bookings/*
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/api/v1/bookings").authenticated()
                .mvcMatchers(HttpMethod.POST, "/api/v1/bookings").hasRole("USER")
                .mvcMatchers(HttpMethod.PUT, "/api/v1/bookings/*").authenticated()
                .mvcMatchers(HttpMethod.DELETE, "/api/v1/bookings/*").authenticated();
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

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
