package org.xenon.silo.archive.shared.filters;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.xenon.silo.archive.auth.domain.JwtService;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       String authHeader = request.getHeader("Authorization");
       String path = request.getServletPath();
       if(path.equals("/auth/refresh")){
           filterChain.doFilter(request,response);
           return;
       }

       if(authHeader == null || !authHeader.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
           return;
       }

       String token = authHeader.replace("Bearer ", "");
       try{
           UUID userId = jwtService.extractUserId(token);
           var authentication = new UsernamePasswordAuthenticationToken(userId, null, Collections.emptyList());
           authentication.setDetails(
                   new WebAuthenticationDetailsSource().buildDetails(request)
           );

           SecurityContextHolder.getContext().setAuthentication(authentication);

       }catch(JwtException | IllegalArgumentException e){
         SecurityContextHolder.clearContext();
         return;
       }

       filterChain.doFilter(request,response);
    }
}
