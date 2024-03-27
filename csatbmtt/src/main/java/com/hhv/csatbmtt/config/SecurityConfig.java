package com.hhv.csatbmtt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hhv.csatbmtt.filter.JwtAuthFilter;
import com.hhv.csatbmtt.service.UserService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 
  
    @Autowired
    private JwtAuthFilter authFilter; 
    
    @Autowired
    private UserService service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers("api/user/login", "api/user/register").permitAll() 
                .anyRequest().authenticated()) 
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); 
    }
    
    @SuppressWarnings("deprecation")
	@Bean
    public AuthenticationProvider authenticationProvider() { 
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(); 
        authenticationProvider.setUserDetailsService(service);  
        authenticationProvider.setPasswordEncoder((NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance());
        return authenticationProvider; 
    } 
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { 
        return config.getAuthenticationManager(); 
    } 
  
  
} 
