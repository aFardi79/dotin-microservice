package ir.cactus.controller;


import io.github.resilience4j.retry.annotation.Retry;
import ir.cactus.model.Product;
import ir.cactus.service.dto.ProductDTO;
import ir.cactus.service.impl.ProductService;
import ir.cactus.shared.product.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CommandGateway commandGateway;


    @PostMapping("/createProduct")
    public void createProduct(@RequestBody ProductDTO product) {
        log.info("Creating product {}", product);
        commandGateway.send(new CreateProductCommand(RandomGenerator.getDefault().nextLong(),product.getName(),product.getPrice(),product.getCouponCode()));
        log.info("Created product {}", product);
    }


    @GetMapping("/findAllProducts")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/getproduct/{id}")
    @Retry(name = "getProduct" ,fallbackMethod = "getProductFallBackMethod")
    public ResponseEntity<Product>getProduct(@PathVariable("id") Long id) {
        log.info("getProductRequest {}",id);
        throw new RuntimeException("error");
    }


    public ResponseEntity<Product>getProductFallBackMethod(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Product("name", BigDecimal.ONE,"DAAA"));
    }
}
