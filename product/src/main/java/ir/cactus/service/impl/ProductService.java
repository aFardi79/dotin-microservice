package ir.cactus.service.impl;


import ir.cactus.discount.DiscountClient;
import ir.cactus.discount.DiscountDTO;
import ir.cactus.model.Product;
import ir.cactus.notification.NotificationClient;
import ir.cactus.notification.NotificationDTO;
import ir.cactus.repository.ProductRepository;
import ir.cactus.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    RestTemplate restTemplate;
//
//    @Autowired
//    RestClient restClient;

    @Autowired
    private DiscountClient discountClient;

    @Autowired
    private NotificationClient notificationClient;


    public void createProduct(ProductDTO product) {
//        DiscountDTO discount = restClient.get()
//                .uri("http://localhost:9090/api/v1/discount/findDiscountByCode/{code}")
//                .retrieve()
//                .body(DiscountDTO.class);
//        DiscountDTO discount=restTemplate.getForObject("http://DISCOUNT/api/v1/discount/findDiscountByCode/{code}", DiscountDTO.class,product.getCouponCode());
        DiscountDTO discountDTO=discountClient.findDiscountByCode(product.getCouponCode());
        product.setPrice(product.getPrice().divide(discountDTO.getPercentage()));
        Product productEntity=generateProduct(product);
        Product productResult =productRepository.save(productEntity);
        if (productResult!=null){
            NotificationDTO notificationDTO=new NotificationDTO();
            notificationDTO.setEntityID(String.valueOf(productResult.getId()));
            notificationDTO.setMessage("the product is created");
            notificationDTO.setTime(LocalDateTime.now().toString());
            notificationDTO.setSender("amir");
            notificationDTO.setEntityName(productResult.getClass().getSimpleName());
            notificationClient.createNotification(notificationDTO);
        }
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
        productEntity.setId(Long.parseLong(product.getId()));
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }


    public Product getProduct(Long id){
        return productRepository.findById(id).get();
    }


    public ProductDTO generateProductDTO(Product product) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(String.valueOf(product.getId()));
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }


}
