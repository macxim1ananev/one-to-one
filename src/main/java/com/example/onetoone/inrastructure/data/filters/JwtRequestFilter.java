//package com.example.onetoone.inrastructure.data.filters;
//
//import com.example.onetoone.config.security.JwtTokenUtil;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//
//@Component
//@RequiredArgsConstructor
//public class JwtRequestFilter extends OncePerRequestFilter {
//
//    private static final String NOT_TOKEN = "This is not a refresh token";
//    private final JwtTokenUtil jwtTokenUtil;
//    private final UserDetailsService userDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        try {
//            String jwtToken = extractJwtFromRequest(request);
//
//            if (StringUtils.hasText(jwtToken) && jwtTokenUtil.validateAccessToken(jwtToken)) {
//                String login = jwtTokenUtil.getEmailFromToken(jwtToken);
//                UserDetails userDetails = userDetailsService.loadUserByUsername(login);
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails.getUsername(), null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//        } catch (ExpiredJwtException ex) {
//            boolean isRefreshToken = Boolean.parseBoolean(request.getHeader("isRefreshToken"));
//            String requestURL = request.getRequestURL().toString();
//            if (isRefreshToken && requestURL.contains("refresh")) {
//                allowForRefreshToken(ex, request);
//            } else
//                request.setAttribute("exception", ex);
//        } catch (JwtException ex) {
//            request.setAttribute("exception", ex);
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                "user", null, Collections.emptyList());
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        request.setAttribute("claims", ex.getClaims());
//    }
//
//    private String extractJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return NOT_TOKEN;
//    }
//}
