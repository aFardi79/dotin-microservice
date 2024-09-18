package ir.cactus.service.impl;


import ir.cactus.model.Discount;
import ir.cactus.model.Product;
import ir.cactus.repository.ProductRepository;
import ir.cactus.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestClient restClient;


    public void createProduct(ProductDTO product) {
//        Discount discount =restClient.get()
//                .uri("http://localhost:9090/api/v1/discount/findDiscountByCode/{code}")
//                        .retrieve()
//                                .toEntity(Discount.class);
        Discount discount=restTemplate.getForObject("http://localhost:9090/api/v1/discount/findDiscountByCode/{code}", Discount.class,product.getCouponCode());
        product.setPrice(product.getPrice().divide(discount.getPercentage()));
        Product productEntity=generateProduct(product);
        productRepository.save(productEntity);
    }

    public List<ProductDTO>findAllProducts() {
        List<Product> products=productRepository.findAll();
        List<ProductDTO> productDTOs=new ArrayList<>();
        for(Product product:products){
            ProductDTO productDTO=generateProductDTO(product);
        }
        return productDTOs;
    }


    public Product generateProduct(ProductDTO product) {
        Product productEntity=new  Product();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }


    public ProductDTO generateProductDTO(Product product) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }


}
