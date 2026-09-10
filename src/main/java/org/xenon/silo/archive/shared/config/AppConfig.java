package org.xenon.silo.archive.shared.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xenon.silo.archive.auth.infrastructure.security.JwtService;
import org.xenon.silo.archive.shared.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppConfig {
    private final JwtService jwtService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){return new JwtAuthenticationFilter(jwtService);}

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
}
