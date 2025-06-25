package com.suiteonix.schoolpaz.kernel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true)
class SecurityConfig {
//    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, ObjectMapper objectMapper) throws Exception {
        http
                .cors(configurer -> configurer.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .rememberMe(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .exceptionHandling(configurer -> {
                    configurer.authenticationEntryPoint((request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.setContentType("application/json");
                        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
                        detail.setTitle("Access Denied");
                        detail.setDetail(accessDeniedException.getMessage());
                        response.getWriter().write(objectMapper.writeValueAsString(detail));
                    });
                    configurer.accessDeniedHandler((request, response, accessDeniedException) -> {
                        response.setStatus(HttpStatus.FORBIDDEN.value());
                        response.setContentType("application/json");
                        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
                        detail.setTitle("");
                        detail.setDetail(accessDeniedException.getMessage());
                        response.getWriter().write(objectMapper.writeValueAsString(detail));
                    });
                })
                .authorizeHttpRequests(registry -> registry
                                .requestMatchers("/v1/auth/**").permitAll()
                                .requestMatchers("/v1/user/onboard").permitAll()
//                        ACTUATOR
                                .requestMatchers(new String[]{"/actuator", "/actuator/*", "/actuator/**"}).permitAll()
//                        ERRORS
                                .requestMatchers(new String[]{"/errors/**", "/errors", "/error"}).permitAll()
//                        DOCUMENTATION
                                .requestMatchers(new String[]{"/documentation", "/docs", "/docs.yaml", "/docs.json", "/docs/**", "/swagger-ui.html", "/swagger-ui/**", "/api-docs", "/api-docs.yaml", "/api-docs/**"}).permitAll()
                                .requestMatchers(new String[]{"/favicon.ico"}).permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, new String[]{"/*", "/**"}).permitAll()
                                .requestMatchers(HttpMethod.GET, "v1/business/utils/industries").permitAll()
                                .requestMatchers(HttpMethod.GET,
                                        "/v1/user/register",
                                        "/v1/location/country/**", "/v1/location/countries/**",
                                        "/v1/location/states/**", "/v1/location/state/**",
                                        "/v1/location/cities/**", "/v1/location/city/**"
                                ).permitAll()
                                .requestMatchers("v1/auth/login", "v1/auth/verify-email", "v1/auth/resend-verification").permitAll()
                                .requestMatchers("v1/business/utils/industries", "v1/business/**", "v1/business/**").permitAll()
                                .anyRequest().authenticated()
                );
//        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedMethod(HttpMethod.POST);
            corsConfiguration.addAllowedMethod(HttpMethod.PATCH);
            corsConfiguration.addAllowedMethod(HttpMethod.PUT);
            corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
            corsConfiguration.addAllowedMethod(HttpMethod.GET);
            corsConfiguration.addAllowedMethod(HttpMethod.HEAD);
            corsConfiguration.addAllowedMethod(HttpMethod.TRACE);
            corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
            corsConfiguration.setAllowedHeaders(List.of("*", "**"));
            corsConfiguration.addAllowedOriginPattern("**");
            corsConfiguration.addAllowedOriginPattern("*");
            corsConfiguration.setAllowedOrigins(List.of("*", "**"));
            corsConfiguration.setExposedHeaders(List.of("*"));
            return corsConfiguration;
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
