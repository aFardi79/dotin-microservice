package ir.cactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ApiGateWayServer {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayServer.class, args);
    }


    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(predicateSpec ->
                        predicateSpec
                                .path("/api/v1/discount/**")
                                .filters(predicateSpecFilters -> predicateSpecFilters.rewritePath("/DISCOUNT/?(?<remaining>.*)", "/${remaining}")
                                        .addResponseHeader("my_header", LocalDateTime.now().toString()))
                                .uri("lb://discount"))
                .route(predicateSpec ->
                        predicateSpec.path("/api/v1/product/**")
                                .uri("lb://PRODUCT"))
                .build();
    }
}
