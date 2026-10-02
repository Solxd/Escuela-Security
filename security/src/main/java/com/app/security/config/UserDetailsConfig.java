package com.app.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailsConfig {

@Bean
public UserDetailsService userDetailsService() {

    UserDetails admin = User.builder()
            .username("admin_Sol")
            .password("{noop}1234")
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(admin);
}

}
