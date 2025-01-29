package com.yassir.bitbox.configurations;

import com.yassir.bitbox.Services.user.CustomUserDetailsService;
import com.yassir.bitbox.dto.item.ItemDTO;
import com.yassir.bitbox.dto.item.SupplierDTO;
import com.yassir.bitbox.models.Item.Item;
import com.yassir.bitbox.models.Item.Supplier;
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
//    @Bean
//    public ModelMapper modelMapper() {
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setDeepCopyEnabled(false);
//        return modelMapper;
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom mapping for Item to ItemDTO
        modelMapper.typeMap(Item.class, ItemDTO.class).addMappings(mapper -> {
            mapper.map(Item::getItemCode, ItemDTO::setItemCode);
            mapper.map(Item::getDescription, ItemDTO::setDescription);
            mapper.map(Item::getSuppliers, ItemDTO::setSuppliers);
        });

        // Custom mapping for Supplier to SupplierDTO
        modelMapper.typeMap(Supplier.class, SupplierDTO.class).addMappings(mapper -> {
            mapper.map(Supplier::getSupplierCode, SupplierDTO::setSupplierCode);
            mapper.map(Supplier::getName, SupplierDTO::setName);
            mapper.map(Supplier::getCountry, SupplierDTO::setCountry);

            // Avoid recursive mapping for items in SupplierDTO
            mapper.skip(SupplierDTO::setItems);
        });

        return modelMapper;
    }

}
