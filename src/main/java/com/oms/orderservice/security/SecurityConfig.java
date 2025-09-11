package com.oms.orderservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/error", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth->oauth
                        .defaultSuccessUrl("/home", true))   // Enables GitHub login
                .logout(logout -> logout
                        .logoutUrl("/logout")                 // Default is /logout
                        .logoutSuccessUrl("/login")               // Redirect here after logout
                        .invalidateHttpSession(true)         // Invalidate session
                        .clearAuthentication(true)           // Clear authentication
                        .deleteCookies("OAUTHSESSIONID")         // Delete session cookies
                        .permitAll()
                );
        return http.build();
    }
}
