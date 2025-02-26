package com.gatway.filter;

import com.gatway.util.JwtUtil;
import com.gatway.validators.RouteValidator;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    logger.warning("Missing Authorization header in request: " + exchange.getRequest().getURI());
                    return setUnauthorizedResponse(exchange, "Missing Authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
                logger.info("Received Authorization Header: " + authHeader);

                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    logger.warning("Invalid Authorization header format");
                    return setUnauthorizedResponse(exchange, "Authorization header must start with 'Bearer '");
                }

                String token = authHeader.substring(7);

                try {
                    jwtUtil.validateToken(token);
                } catch (Exception e) {
                    logger.severe("Invalid Token: " + e.getMessage());
                    return setUnauthorizedResponse(exchange, "Invalid Token");
                }
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String message) {
        logger.warning("Unauthorized access: " + message);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}