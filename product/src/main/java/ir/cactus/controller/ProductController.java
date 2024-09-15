package ir.cactus.controller;


import ir.cactus.model.Product;
import ir.cactus.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping("/createProduct")
    public void createProduct(@RequestBody Product product) {

        productService.createProduct(product);
    }

}
