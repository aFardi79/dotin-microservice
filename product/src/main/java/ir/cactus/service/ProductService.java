package ir.cactus.service;


import ir.cactus.model.Discount;
import ir.cactus.model.Product;
import ir.cactus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestClient restClient;


    public void createProduct(Product product) {
//        Discount discount =restClient.get()
//                .uri("http://localhost:9090/api/v1/discount/findDiscountByCode/{code}")
//                        .retrieve()
//                                .toEntity(Discount.class);
        Discount discount=restTemplate.getForObject("http://localhost:9090/api/v1/discount/findDiscountByCode/{code}", Discount.class,product.getCouponCode());
        product.setPrice(product.getPrice().divide(discount.getPercentage()));
        productRepository.save(product);
    }
}
