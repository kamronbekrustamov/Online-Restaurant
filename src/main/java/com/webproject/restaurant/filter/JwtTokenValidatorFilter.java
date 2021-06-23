package com.webproject.restaurant.filter;

import com.webproject.restaurant.model.User;
import com.webproject.restaurant.service.UserService;
import com.webproject.restaurant.util.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@Slf4j
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            String email = null;
            User user = null;
            try {
                email = JwtUtils.extractUsername(token);
                if (JwtUtils.isTokenExpired(token)) {
                    log.warn("Token expired");
                } else {
                    try {
                        user = (User) userService.loadUserByUsername(email);
                        Authentication authentication =
                                new UsernamePasswordAuthenticationToken(
                                        user,
                                        user.getPassword(),
                                        user.getAuthorities()
                                );
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } catch (UsernameNotFoundException e) {
                        log.warn("User does not exist");
                    }
                }
            } catch (Exception e) {
                log.warn("Invalid Token.");
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/api/v1/users/authenticate");
    }
}
