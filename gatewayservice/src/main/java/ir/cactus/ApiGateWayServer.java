package ir.cactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGateWayServer {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServer.class, args);
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter) {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec
                                .path("/api/v1/discount/**")
                                .filters(predicateSpecFilters -> predicateSpecFilters.rewritePath("/DISCOUNT/?(?<remaining>.*)", "/${remaining}")
                                        .addResponseHeader("my_header", LocalDateTime.now().toString())
                                        .requestRateLimiter(config->config.setRateLimiter(redisRateLimiter())
                                                .setKeyResolver(keyResolver()))
                                        .retry(retryConfig ->
                                                retryConfig.setRetries(3)
                                                        .setMethods(HttpMethod.GET)
                                                        .setBackoff(Duration.ofMillis(100),Duration
                                                                .ofMillis(1000),2,true)))
                                .uri("lb://discount"))
                .route(predicateSpec ->
                        predicateSpec.path("/api/v1/product/**")
                                .filters(gatewayFilterSpec -> gatewayFilterSpec.circuitBreaker(config->config.setName("circuteBreaker").setFallbackUri(
                                        "/circuitbreaker/fallback"
                                )))
                                .uri("lb://PRODUCT"))
                .build();
    }

    @Bean
    KeyResolver keyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("user2");
    }

    @Bean
    RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1,1,1);
    }
}
