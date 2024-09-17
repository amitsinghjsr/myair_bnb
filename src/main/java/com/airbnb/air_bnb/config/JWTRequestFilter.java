package com.airbnb.air_bnb.config;

import com.airbnb.air_bnb.entities.PropertyUser;
import com.airbnb.air_bnb.repositories.PropertyUserRepository;
import com.airbnb.air_bnb.service.impl.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private PropertyUserRepository propertyUserRepository;

    public JWTRequestFilter(JwtService jwtService, PropertyUserRepository propertyUserRepository) {
        this.jwtService = jwtService;
        this.propertyUserRepository = propertyUserRepository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader("Authorization");
        if (tokenHeader!=null && tokenHeader.startsWith("Bearer")){
            String token = tokenHeader.substring(8, tokenHeader.length()-1);
            String username = jwtService.getUserName(token);
            Optional<PropertyUser> opUser = propertyUserRepository.findByUsername(username);
            if (opUser.isPresent()){
                PropertyUser user = opUser.get();

                //To keep track of current user logged in
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken
                                (user, null, Collections.singleton(new SimpleGrantedAuthority(user.getUserRole())));
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);

    }
}
