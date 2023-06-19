package com.goupgoup_backend.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private String[] checkURL = {"/oauth2/authorize"};
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
//        if(Arrays.asList(checkURL).contains(request.getServletPath())) {
//            filterChain.doFilter(request, response);
//        } else {
//            if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token) == 1) {
//                // 유효 토큰
//                Authentication authentication = jwtTokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } else if (jwtTokenProvider.validateToken(token) == 2) {
//                // 만료 토큰
//                response.sendError(401, "만료");
//            } else {
//                // 잘못된 토큰
//                response.sendError(500, "잘못된 토큰값");
//            }
//            filterChain.doFilter(request, response);
//        }

        if(token != null && jwtTokenProvider.validateToken(token) == 1) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);

    }
}