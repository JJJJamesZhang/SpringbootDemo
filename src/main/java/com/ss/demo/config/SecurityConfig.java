package com.ss.demo.config;

import com.ss.demo.filter.JWTAuthenticationFilter;
import com.ss.demo.filter.JWTLoginFilter;
import com.ss.demo.service.RedisService;
import com.ss.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;


    //config authenticate thru
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/login").permitAll()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager(),redisService))
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),redisService));
    }

    //customize how to authenticate
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new CustomAuthenticationProvider(userService,redisService));
    }
}
