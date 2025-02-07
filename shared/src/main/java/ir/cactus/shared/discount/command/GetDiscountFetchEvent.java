package ir.cactus.shared.discount.command;

import java.math.BigDecimal;

public record GetDiscountFetchEvent(String code, BigDecimal percentage) {
}
