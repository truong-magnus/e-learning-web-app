package com.elearning.apigateway.filter;

import com.elearning.apigateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationGatewayFilterFactory extends AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            // DEBUG TRAP: Print the path of the incoming request to the console
            System.out.println(">>> GATEWAY FILTER HIT! Request Path: " + exchange.getRequest().getURI().getPath());

            // 1. Check if the requested route requires authentication
            if (validator.isSecured.test(exchange.getRequest())) {

                // 2. Extract the Authorization header
                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                // 3. If the header is completely missing, block the request
                if (authHeader == null) {
                    System.out.println(">>> BLOCKING: Missing Authorization Header");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // 4. Remove the "Bearer " prefix to get the raw JWT token
                if (authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                try {
                    // 5. Validate the extracted token using JwtUtil
                    jwtUtil.validateToken(authHeader);
                    System.out.println(">>> SUCCESS: Token is valid! Passing to downstream service...");
                } catch (Exception e) {
                    // 6. If token is invalid or expired, block the request
                    System.out.println(">>> BLOCKING: Invalid or Expired Token");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } else {
                System.out.println(">>> INFO: Route is public. Bypassing token validation...");
            }

            // 7. Proceed to the next filter in the chain
            return chain.filter(exchange);
        });
    }

    public static class Config {
        // Can be left empty, required by Spring Cloud Gateway architecture
    }
}