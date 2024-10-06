package com.riwi.riwiproject.Infrastructure.Security.Configuration;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public record TokenJwtConfig() {
    // clase de configuracion del token

    public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    public static final String PREFIX_TOKEN = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE= "application/json";
    public static final long EXPIRATION_TIME = 36000000;
// generando clave secreta
    // protegidas en el backend
}
