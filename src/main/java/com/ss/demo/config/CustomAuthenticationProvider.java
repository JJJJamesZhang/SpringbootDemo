package com.ss.demo.config;

import com.ss.demo.entity.User;
import com.ss.demo.service.RedisService;
import com.ss.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;



public class CustomAuthenticationProvider implements AuthenticationProvider {

    public CustomAuthenticationProvider(UserService userService, RedisService redisService) {
        this.userService = userService;
        this.redisService = redisService;
    }

    private RedisService redisService;
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.findUser(username,password);

        Authentication auth = new UsernamePasswordAuthenticationToken(username,password,null);

        if (user == null){
            auth.setAuthenticated(false);
            return null;
        }

        return auth;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
