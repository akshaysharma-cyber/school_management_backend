package com.school.management.School.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.school.management.School.utility.JwtFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter
    ) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain security(
            HttpSecurity http
    ) throws Exception {

        http

            // ENABLE CORS
            .cors(
                cors -> {}
            )

            // DISABLE CSRF
            .csrf(
                csrf -> csrf.disable()
            )

            // DISABLE LOGIN PAGE
            .formLogin(
                login -> login.disable()
            )

            // DISABLE BASIC AUTH
            .httpBasic(
                basic -> basic.disable()
            )

            .authorizeHttpRequests(
                auth -> auth

                    // Allow browser preflight
                    .requestMatchers(
                        HttpMethod.OPTIONS,
                        "/**"
                    )
                    .permitAll()

                    // Public APIs
                    .requestMatchers(
                        "/api/auth/**",
                        "/api/fees/receipt/**",
                        "/api/subjects-by-class",
                        "/api/exams/**",
                        "/api/marks/**",
                        "/api/report-card/**"
                    )
                    .permitAll()

                    // Secure APIs
                    .anyRequest()
                    .authenticated()
            )

            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}
