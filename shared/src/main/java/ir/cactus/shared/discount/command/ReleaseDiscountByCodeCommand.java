package ir.cactus.shared.discount.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ReleaseDiscountByCodeCommand(@TargetAggregateIdentifier String code) {
}
