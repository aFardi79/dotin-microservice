package ir.cactus.controller;


import ir.cactus.model.Product;
import ir.cactus.service.dto.ProductDTO;
import ir.cactus.service.impl.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping("/createProduct")
    public void createProduct(@RequestBody ProductDTO product) {
        productService.createProduct(product);
    }


    @GetMapping("/findAllProducts")
    public ResponseEntity<List<ProductDTO>> findAllProducts() {
        return new ResponseEntity<>(productService.findAllProducts(),new HttpHeaders(), HttpStatus.OK);
    }

}
