package com.elearning.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF to allow Postman requests without tokens
                .csrf(csrf -> csrf.disable())
                // All requests passing through the gateway must be authenticated
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                // Enable Basic Authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Create a custom static user to replace the auto-generated password
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("truong") // Your custom VIP username
                .password("123456") // Your custom VIP password
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}