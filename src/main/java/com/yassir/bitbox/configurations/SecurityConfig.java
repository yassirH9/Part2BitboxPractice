package com.yassir.bitbox.configurations;

import com.yassir.bitbox.Services.user.CustomUserDetailsService;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.PriceReductionDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import com.yassir.bitbox.dto.user.UserDTO;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.PriceReduction;
import com.yassir.bitbox.models.Item.Supplier;
import com.yassir.bitbox.models.user.User;
import com.yassir.bitbox.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired private IUserRepository IUserRepository;

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers(
                                // Disable CSRF for this endpoint
                                "/api/user/register",
                                "/h2-console/**",
                                "/api/**"
                                )
                )
                .authorizeHttpRequests(auth -> auth
                        //H2 ACCESS TEMPORARY FOR DEBUG
                        .requestMatchers("/h2-console/**").permitAll()
                        //API
                        .requestMatchers("/api/user/register").permitAll() // Public endpoint
                        .requestMatchers("/api/user/admin/**").hasAuthority("ADMIN") // Only users with ADMIN privilege can access
                        .requestMatchers("/api/**").hasAnyAuthority("USER", "ADMIN") // Only users with USER privilege can access
                        .anyRequest().authenticated() // All other endpoints require authentication
                )
                .httpBasic(httpBasic -> {}) // Enable Basic Authentication
                .userDetailsService(userDetailsService); // Use your custom UserDetailsService

        // Allow frames for H2 Console (needed for H2's embedded UI)
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));

        return http.build();
    }

    @Bean
    public CustomUserDetailsService userDetailsService() {
        return new CustomUserDetailsService(IUserRepository);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
