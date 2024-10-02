package ir.cactus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class DiscountApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscountApplication.class, args);
    }
}
