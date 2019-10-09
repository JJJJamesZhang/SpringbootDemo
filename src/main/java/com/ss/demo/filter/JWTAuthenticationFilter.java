package com.ss.demo.filter;

import com.ss.demo.service.RedisService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    RedisService redisService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, RedisService redisService) {
        super(authenticationManager);
        this.redisService = redisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");

        if (token == null || !token.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(token);
        if (authenticationToken != null){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request,response);
        }else {
            throw new RuntimeException("No Auth");
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token){
        Claims claims = Jwts.parser().setSigningKey("MyJwtToken")
                .parseClaimsJws(token.replace("Bearer ","")).getBody();
        String username = claims.getSubject();
        Date expiration = claims.getExpiration();
        Date now = new Date();

        if (now.getTime() > expiration.getTime()){
            throw new RuntimeException("Token expiration");
        }
        String redisToken = (String) redisService.get(username);
        if (username != null && token.replace("Bearer ","").equals(redisToken)){
            return new UsernamePasswordAuthenticationToken(username,"",null);
        }
        return null;
    }
}
