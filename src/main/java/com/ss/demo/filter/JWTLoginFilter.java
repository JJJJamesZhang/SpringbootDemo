package com.ss.demo.filter;

import com.ss.demo.service.RedisService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private RedisService redisService;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager, RedisService redisService) {
        this.authenticationManager = authenticationManager;
        this.redisService = redisService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null){
            username = "";
        }
        if (password == null){
            password = "";
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password,null);
        Authentication authentication = authenticationManager.authenticate(token);

        if (!authentication.isAuthenticated()){
            try {
                response.sendRedirect("/NoAuth");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder().setSubject(authResult.getName())
                .setExpiration(new Date(System.currentTimeMillis()+ 60*60*2*1000))
                .signWith(SignatureAlgorithm.HS512,"MyJwtToken").compact();
        response.addHeader("token","Bearer "+token);
        redisService.save(authResult.getName(),token);
    }
}
