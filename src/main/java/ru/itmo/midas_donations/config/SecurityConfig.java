package ru.itmo.midas_donations.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeHttpRequests(authorize -> {
                            authorize.requestMatchers(
                                    //new AntPathRequestMatcher("/api/api-docs"),
                                    //new AntPathRequestMatcher("/webjars/**"),
                                    new AntPathRequestMatcher("/api/donations")
                            ).permitAll();
                            authorize.anyRequest().denyAll();
                        }
                ).build();
    }
}

