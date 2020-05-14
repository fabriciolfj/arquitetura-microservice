package br.com.spark.gateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

//@Configuration
public class RouteConfig {

    //@Bean nao deu certo falha na lib
    public RouteLocator hostsHosts(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/produtos/*", "/produtos*")
                        .uri("lb://produto-service") //dentro do eureka usa-se lb
                        .id("produto-service"))
                .route(r -> r.path("/inventario*", "/inventario/*")
                        .filters(f -> f.circuitBreaker(c -> c.setName("myCircuiteBreaker")
                                .setFallbackUri("forward:/inventario-failover")))
                        .uri("lb://inventario-service")
                        .id("inventario-service"))
                .route(r -> r.path( "/inventario-failover")
                        .uri("lb://inv-failover")
                        .id("inv-failover"))
                .build();
    }

    //@Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build()).build());
    }
}
