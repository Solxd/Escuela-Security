package com.app.Gateway.config;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .cors(Customizer.withDefaults())   // usa el bean corsConfigurationSource de CorsConfig

            .authorizeExchange(exchanges -> exchanges

                // Preflight de CORS (el navegador/Angular lo manda sin token)
                .pathMatchers(HttpMethod.OPTIONS).permitAll()

                // Rutas públicas: login y swagger
                .pathMatchers(
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/webjars/**"
                ).permitAll()

                // Todo lo demás: JWT válido + rol ADMIN o DOCENTE
                // (es lo mismo que tenías en los micros con hasAnyRole)
                .anyExchange().hasAnyRole("ADMIN", "DOCENTE")
            )

            .oauth2ResourceServer(oauth ->
                oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
            )
            .build();
    }

    // Tu security guarda el rol en el claim "role" con valor "ROLE_ADMIN".
    // Este converter le dice al gateway que lea ese claim y NO agregue otro prefijo.
    private ReactiveJwtAuthenticationConverterAdapter jwtAuthConverter() {
        JwtGrantedAuthoritiesConverter authorities = new JwtGrantedAuthoritiesConverter();
        authorities.setAuthoritiesClaimName("role");
        authorities.setAuthorityPrefix("");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authorities);
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }

    // Tiene que usar EXACTAMENTE la misma clave que JwtTokenProvider (security).
    // Tu provider firma con HS256 y la clave en texto plano (UTF-8), por eso se arma así.
    @Bean
    public ReactiveJwtDecoder jwtDecoder(@Value("${jwt.secret}") String secret) {
        SecretKey key = new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");

        return NimbusReactiveJwtDecoder
                .withSecretKey(key)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
