package com.riwi.riwiproject.Infrastructure.Security.Filter;

import com.riwi.riwiproject.Infrastructure.Security.Configuration.JWTUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.riwi.riwiproject.Infrastructure.Security.Configuration.TokenJwtConfig.*;


@Component
public class AutenticationFilterWithOnce extends OncePerRequestFilter {



    @Autowired
    private  JWTUtils jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("iniciando sesion");
        String header = request.getHeader(HEADER_AUTHORIZATION);
        String token = null;
        String username = null;

        // Verificar si el encabezado contiene el token JWT
        if (header != null && header.startsWith(PREFIX_TOKEN)) {
            token = header.substring(7);
            username = jwtUtil.extractUsername(token);
            System.out.println("iniciando sesion2");
        }

        // Si se obtiene un username y no hay autenticación previa en el contexto
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("iniciando sesion3");
            // Validar el token y configurar la autenticación
            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("iniciando sesion4");
            }
        }

        chain.doFilter(request, response);
    }
}
