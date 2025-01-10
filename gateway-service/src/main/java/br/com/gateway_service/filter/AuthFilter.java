package br.com.gateway_service.filter;

import org.apache.http.HttpHeaders;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
@RefreshScope
public class AuthFilter implements GlobalFilter, Ordered {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private final static int HIGH_PRIORITY = -1;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("Start Auth Filter in path: " + exchange.getRequest().getPath());

        try {
            String requestPath = exchange.getRequest().getURI().getPath();

            if (!requestPath.equals("/auth-service/login")) {
                exchange.getRequest().mutate()
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                        .build();
            }
        } catch (Exception exception) {
            log.warning(exception.getMessage());
            return unauthorized(exchange);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return HIGH_PRIORITY;
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
