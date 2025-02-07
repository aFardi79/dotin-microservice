package ir.cactus.shared.product.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CreateProductCommand(@TargetAggregateIdentifier Long id, String name, BigDecimal price,String discountCode) {
}
