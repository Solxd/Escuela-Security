package com.app.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InternalSecretFilter extends OncePerRequestFilter {

    @Value("${app.internal-secret}")
    private String internalSecret;

    // Estas rutas no pasan por el gateway (la página de swagger, actuator para AdminServer).
    // Los endpoints de negocio (/alumnos, /cursos, /personal) SÍ quedan protegidos.
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/swagger-ui")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/h2-console")
            || path.startsWith("/actuator");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("X-Internal-Secret");

        if (!internalSecret.equals(header)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Acceso directo no permitido. Usar el gateway.\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}