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

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .anyRequest().permitAll()  // Allow all requests
                )
                .csrf((csrf) -> csrf. disable())
                .formLogin((formlogin)-> formlogin.disable())
                .httpBasic((httpBasic)-> httpBasic.disable())
                .headers((headers) -> headers
                        .frameOptions((frameOptions) -> frameOptions.disable())  // Disable frame options on headers (quick fix for h2 database console)
                );
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
