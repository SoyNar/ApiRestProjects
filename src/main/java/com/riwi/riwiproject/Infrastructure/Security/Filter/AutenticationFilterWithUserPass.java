//package com.riwi.riwiproject.Infrastructure.Security.Filter;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.riwi.riwiproject.Infrastructure.Security.Configuration.JWTUtils;
//import com.riwi.riwiproject.domain.Model.User;
//import io.jsonwebtoken.io.IOException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import static com.riwi.riwiproject.Infrastructure.Security.Configuration.TokenJwtConfig.*;
//
//
////autenticar usuario a traves de username y contraseña
//
//public class AutenticationFilterWithUserPass extends UsernamePasswordAuthenticationFilter {
//
//
//    public AutenticationFilterWithUserPass(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
////    @Override
////    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
////        // Leer el objeto User directamente
////
////        System.out.println(" iniciando sesion");
////        User user = null;
////        try {
////            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
////        } catch (java.io.IOException e) {
////            throw new RuntimeException(e);
////        }
////
////        // Aquí puedes crear el objeto de autenticación
////        UsernamePasswordAuthenticationToken authRequest =
////                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
////
////        return this.getAuthenticationManager().authenticate(authRequest);
////
////        }
////
////    @Override
////    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException, java.io.IOException {
////        // Obtener el nombre de usuario del objeto de autenticación
////        System.out.println("ingreso desde metodo password");
////        org.springframework.security.core.userdetails.User userS =
////                (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
////        String userName = userS.getUsername();
////
////        // Obtener los roles del usuario
////        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
////
////        // Convertir roles a un formato adecuado (usando el enum)
////        List<String> roles = authorities.stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.toList());
////
////        // Crear claims para el token
////        Map<String, Object> claims = new HashMap<>();
////        claims.put("roles", roles); // Agregar los roles a los claims
////
////        // Usar JWTUtils para generar el token
////        JWTUtils jwtUtils = new JWTUtils(); // Instanciar JWTUtils
////        String token = jwtUtils.createToken(claims, userName); // Usar createToken para generar el token
////
////        // Agregar el token en el encabezado de la respuesta
////        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
////
////        // Preparar la respuesta JSON
////        Map<String, String> body = new HashMap<>();
////        body.put("token", token);
////        body.put("username", userName);
////        body.put("message", String.format("Hola %s, has iniciado sesión con éxito", userName));
////        // Escribir la respuesta en JSON
////        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
////        response.setContentType(CONTENT_TYPE);
////        response.setStatus(HttpServletResponse.SC_OK);
////   }
////
////    @Override
////    protected void unsuccessfulAuthentication(HttpServletRequest request,
////                                              HttpServletResponse response, AuthenticationException failed)
////            throws java.io.IOException, ServletException {
////            Map<String, String> body = new HashMap<>();
////            body.put("messege","Error en la autenticacion username o password incorrectos");
////            body.put("error", failed.getMessage());
////
////            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
////            response.setStatus(401);
////            response.setContentType(CONTENT_TYPE);
////    }
//}