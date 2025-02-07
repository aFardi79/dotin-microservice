package ir.cactus.repository.handler;


import ir.cactus.model.Discount;
import ir.cactus.repository.DiscountRepository;
import ir.cactus.shared.discount.command.GetDiscountCommand;
import ir.cactus.shared.discount.command.GetDiscountFetchEvent;
import ir.cactus.shared.discount.events.DiscountCreatedEvent;
import ir.cactus.shared.discount.events.DiscountReleaseEvent;
import ir.cactus.shared.discount.events.DiscountReservedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountEventHandler {

    private final DiscountRepository discountRepository;

    public DiscountEventHandler(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }


    @EventHandler
    public void on(DiscountCreatedEvent event) {
        Discount discount = new Discount();
        discount.setCode(event.code());
        discount.setCount(event.count());
        discount.setExpireDate(event.expiryDate());
        discountRepository.save(discount);
    }

    @EventHandler
    public void on(DiscountReservedEvent event) {
        Discount discount = discountRepository.findDiscountByCode(event.code());
        discount.setCount(discount.getCount() - 1);
        discountRepository.save(discount);
    }

    @EventHandler
    public void on(GetDiscountCommand command) {
        BigDecimal discountPercentage = discountRepository.findDiscountByCode(command.code()).getPercentage();
        GetDiscountFetchEvent discountFetchedEvent = new GetDiscountFetchEvent(command.code(), discountPercentage);
    }


    @EventHandler
    public void on(DiscountReleaseEvent event) {
        Discount discount = discountRepository.findDiscountByCode(event.code());
        discount.setCount(discount.getCount() + 1);
        discountRepository.save(discount);
    }
}
