//package filter;  // ✅ Ensure it matches the correct package structure
package com.gatway.filter;

import com.gatway.util.JwtUtil;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
//import util.JwtUtil;
import com.gatway.validators.RouteValidator;
//import com.gateway.util.JwtUtil;
//import com.gateway.validators.RouteValidator;

import java.util.logging.Logger;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private static final Logger logger = Logger.getLogger(AuthenticationFilter.class.getName());

    @Autowired
    private RouteValidator.RouteValidator validator;

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
                logger.info("Forwarded Authorization Header: " + authHeader);

                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                } else {
                    logger.warning("Authorization header does not start with 'Bearer '");
                    return setUnauthorizedResponse(exchange, "Authorization header must start with 'Bearer '");
                }

                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    logger.severe("Invalid Token: " + e.getMessage());
                    return setUnauthorizedResponse(exchange, "Invalid Token");
                }
            }
            return chain.filter(exchange);
        };
    }

    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties if needed
    }
}
