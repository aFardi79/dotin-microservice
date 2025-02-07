package ir.cactus.shared.discount.events;

import java.math.BigDecimal;

public record DiscountReservedEvent(String code, BigDecimal percentage) {
}
