package com.abubakar.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtTokenValidator extends OncePerRequestFilter {


@Override
protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {


    String path = request.getRequestURI();


    // IMPORTANT: skip public APIs
 // Public APIs
if (path.startsWith("/home")
        || path.startsWith("/api/products")
        || path.startsWith("/api/auth")
        || path.equals("/api/create-order")
        || path.equals("/api/verify-payment")) {

    filterChain.doFilter(request, response);
    return;
}


    String header = request.getHeader("Authorization");


    if(header == null || !header.startsWith("Bearer ")) {

        filterChain.doFilter(request,response);
        return;
    }



    try {


        String token = header.substring(7);


        Claims claims = Jwts.parserBuilder()
                .setSigningKey(
                    Keys.hmacShaKeyFor(
                        JWT_CONSTANT.SECRET_KEY.getBytes()
                    )
                )
                .build()
                .parseClaimsJws(token)
                .getBody();



        String email =
                claims.get("email", String.class);



        String authorities =
                claims.get("authorities", String.class);



        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    AuthorityUtils
                    .commaSeparatedStringToAuthorityList(
                        authorities
                    )
                );


        SecurityContextHolder
            .getContext()
            .setAuthentication(auth);



    } catch(Exception e) {

        response.setStatus(401);
        response.getWriter()
                .write("Invalid JWT");

        return;
    }



    filterChain.doFilter(request,response);

}

}