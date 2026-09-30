package com.app.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    // Clave secreta (mínimo 32 caracteres para algoritmo HS256)
    private final String SECRET_KEY = "MiClaveSecretaSuperSeguraParaElColegio123456";
    private final long EXPIRATION_TIME = 86400000; // 24 horas en milisegundos

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generarToken(String username, String role) {
        Date ahora = new Date();
        Date fechaExpiracion = new Date(ahora.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(ahora)
                .setExpiration(fechaExpiracion)
                .signWith(getSigningKey())
                .compact();
    }

    public String obtenerUsernameDelToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}