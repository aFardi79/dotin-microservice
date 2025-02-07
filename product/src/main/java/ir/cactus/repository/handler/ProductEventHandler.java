package ir.cactus.repository.handler;

import ir.cactus.model.Product;
import ir.cactus.repository.ProductRepository;
import ir.cactus.shared.product.command.CreateProductCommand;
import ir.cactus.shared.product.events.CreateProductEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
public class ProductEventHandler {

    private final ProductRepository productRepository;
    private final CommandGateway commandGateway;

    public ProductEventHandler(ProductRepository productRepository, CommandGateway commandGateway) {
        this.productRepository = productRepository;
        this.commandGateway = commandGateway;
    }


    @EventHandler
    public void on (CreateProductEvent event) {
        Product product= new Product();
        product.setId(event.id());
        product.setName(event.name());
        product.setPrice(event.price());
        productRepository.save(product);
    }

}
