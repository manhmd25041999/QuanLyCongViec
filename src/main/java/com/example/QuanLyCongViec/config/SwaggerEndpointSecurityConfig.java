package com.example.QuanLyCongViec.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Order(1)
@Configuration
public class SwaggerEndpointSecurityConfig extends WebSecurityConfigurerAdapter {
    private final Logger Logger = LoggerFactory.getLogger(SwaggerEndpointSecurityConfig.class);

    private final String[] SWAGGER_PREFIX = new String[]{
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/dev/**"
    };

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("swagger")
                .password(passwordEncoder().encode("rabiloo"))
                .roles("SWAGGER_ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        Logger.info("Enable swagger endpoint security.");
        http
                .requestMatchers()
                .antMatchers(SWAGGER_PREFIX)
                .and()
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole("SWAGGER_ADMIN"))
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
