package ir.cactus.shared.discount.events;

import java.math.BigDecimal;

public record DiscountCreatedEvent(Long id,String code, BigDecimal percentage,String expiryDate,Long count) {
}
