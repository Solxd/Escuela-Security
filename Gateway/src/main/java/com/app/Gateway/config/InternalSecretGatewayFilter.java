package com.app.Gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class InternalSecretGatewayFilter implements GlobalFilter, Ordered {

    @Value("${app.internal-secret}")
    private String internalSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest().mutate()
            .headers(headers -> {
                headers.remove("X-Internal-Secret");          // por si el cliente intenta mandarlo
                headers.add("X-Internal-Secret", internalSecret);
            })
            .build();

        return chain.filter(exchange.mutate().request(request).build());
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
