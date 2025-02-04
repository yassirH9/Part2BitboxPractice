package com.yassir.bitbox.configurations;

import com.yassir.bitbox.Services.user.CustomUserDetailsService;
import com.yassir.bitbox.configurations.jwt.JwtAuthenticationFilter;
import com.yassir.bitbox.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired private IUserRepository IUserRepository;

    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf
//                        .ignoringRequestMatchers(
//                                // Disable CSRF for this endpoint
//                                "/api/user/register",
//                                "/h2-console/**",
//                                "/api/**"
//                                )
//                )
//                .authorizeHttpRequests(auth -> auth
//                        //H2 ACCESS TEMPORARY FOR DEBUG
//                        .requestMatchers("/h2-console/**").permitAll()
//                        //API
//                        .requestMatchers("/api/user/register").permitAll() // Public endpoint
//                        .requestMatchers("/api/user/admin/**").hasAuthority("ADMIN") // Only users with ADMIN privilege can access
//                        .requestMatchers("/api/item/delete/**").hasAuthority("ADMIN")
//                        .requestMatchers("/api/**").hasAnyAuthority("USER", "ADMIN") // Only users with USER privilege can access
//                        .anyRequest().authenticated() // All other endpoints require authentication
//                )
//                .httpBasic(httpBasic -> {}) // Enable Basic Authentication
//                .userDetailsService(userDetailsService); // Use your custom UserDetailsService
//
//        // Allow frames for H2 Console (needed for H2's embedded UI)
//        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
//
//        return http.build();
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                "/api/user/register",
                                "/api/user/login",
                                "/h2-console/**"

                        )
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/user/login").permitAll()
                        .requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/user/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/item/delete/**").hasAuthority("ADMIN")
                        .requestMatchers("/api/**").hasAnyAuthority("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(userDetailsService);

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

//    @Bean
//    public CustomUserDetailsService userDetailsService() {
//        return new CustomUserDetailsService(IUserRepository);
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
