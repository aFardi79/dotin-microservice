package ir.cactus.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackProdcutController {


    @RequestMapping("/circuitbreaker/fallback")
    public Mono<ResponseEntity<String>> contactSupport(){
        return Mono.just(ResponseEntity.ok("Hello World"));
    }
}
