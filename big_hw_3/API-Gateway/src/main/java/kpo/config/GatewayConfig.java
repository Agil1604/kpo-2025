package kpo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("order", r -> r.path("/api/order/**")
                        .uri("http://orders-service:8080"))
                .route("payment", r -> r.path("/api/payment/**")
                        .uri("http://payments-service:8080"))
                .route("order-api-docs", r -> r.path("/v3/api-docs/orders")
                        .filters(f -> f.rewritePath("/v3/api-docs/orders", "/v3/api-docs"))
                        .uri("http://orders-service:8080"))
                .route("payments-api-docs", r -> r.path("/v3/api-docs/payments")
                        .filters(f -> f.rewritePath("/v3/api-docs/payments", "/v3/api-docs"))
                        .uri("http://payments-service:8080"))
                .build();
    }
}