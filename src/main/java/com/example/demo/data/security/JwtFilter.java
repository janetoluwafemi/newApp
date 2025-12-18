package com.example.demo.data.security;

import com.example.demo.data.dto.responses.FindUserEmailResponse;
import com.example.demo.data.services.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtility jwtUtility;
    private final UserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || header.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        if(!header.isEmpty() && header.startsWith("Bearer"))  {
            String token = header.substring(7);
            if (token != null) {
                String email = String.valueOf(jwtUtility.getToken(token));
                if (email != null) {
                    UserDetails userDetails = userService.loadUserByUsername(email);
                    request.setAttribute("user", userDetails);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    FindUserEmailResponse findUserEmailResponse = ((UserServiceImpl) userService).findUserEmailResponse(email);
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
