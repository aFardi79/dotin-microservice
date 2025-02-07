package ir.cactus.shared.discount.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ReserveDiscountCommand(@TargetAggregateIdentifier String code,Long count) {
}
