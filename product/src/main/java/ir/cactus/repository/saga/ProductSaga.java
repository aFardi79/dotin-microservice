package ir.cactus.repository.saga;

import com.netflix.discovery.converters.Auto;
import ir.cactus.shared.discount.command.GetDiscountCommand;
import ir.cactus.shared.discount.command.GetDiscountFetchEvent;
import ir.cactus.shared.notification.commands.CreateNotificationCommand;
import ir.cactus.shared.notification.events.CreateNotificationEvent;
import ir.cactus.shared.product.events.CreateProductEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.random.RandomGenerator;

@Saga
@Slf4j
public class ProductSaga {

    @Autowired
    private transient CommandGateway commandGateway;


    @StartSaga
    @SagaEventHandler(associationProperty = "id")
    public void handle(CreateProductEvent event) {
        log.info("Received CreateProductEvent: {}", event);
        if (event.discountCode()!=null){
            commandGateway.send(new GetDiscountCommand(event.discountCode()));
        }else{
            saveProductAndNotify(event.id(),event.price());
        }
    }
    @SagaEventHandler(associationProperty = "code")
    public void handle(GetDiscountFetchEvent event) {
        BigDecimal finalPrice = calculateDiscountedPrice(event.percentage());
        saveProductAndNotify(RandomGenerator.getDefault().nextLong(), finalPrice);
    }

    private BigDecimal calculateDiscountedPrice(BigDecimal discountPercentage) {
        return discountPercentage.divide(new BigDecimal(100.0)); // Sample calculation
    }
    private void saveProductAndNotify(Long productId, BigDecimal finalPrice) {
       log.info("Saving product with final price: {}" , finalPrice);
        commandGateway.send(new CreateNotificationCommand(productId, "Product saved successfully with price: " + finalPrice,"notifiactionSend","PRODUCT","now","model.product"));
    }
}
