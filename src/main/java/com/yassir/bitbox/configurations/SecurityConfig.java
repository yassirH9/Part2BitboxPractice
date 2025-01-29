package com.yassir.bitbox.configurations;

import com.yassir.bitbox.Services.user.CustomUserDetailsService;
import com.yassir.bitbox.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

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
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setDeepCopyEnabled(false);
        return modelMapper;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
