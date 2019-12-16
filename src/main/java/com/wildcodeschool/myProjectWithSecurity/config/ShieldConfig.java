package com.wildcodeschool.myProjectWithSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class ShieldConfig {
    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User.UserBuilder users = User.builder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("Steve").password(encoder.encode("motdepasse")).roles("CHAMPION").build());
        manager.createUser(users.username("Nick").password(encoder.encode("flerken")).roles("DIRECTOR").build());
        return manager;
    }
    @Configuration
    @Order(1)
    public static class DirectorSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/avengers/assemble")
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().hasRole("CHAMPION")
                    )
                    .httpBasic(Customizer.withDefaults());
        }
    }
    @Configuration
    @Order(2)
    public static class ChampionSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/secret-bases")
                    .authorizeRequests(authorizeRequests ->
                            authorizeRequests
                                    .anyRequest().hasRole("DIRECTOR")
                    )
                    .httpBasic(Customizer.withDefaults());
        }
    }
    @Configuration
    public static class AllSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/")
                    .formLogin(Customizer.withDefaults());
        }
    }
}