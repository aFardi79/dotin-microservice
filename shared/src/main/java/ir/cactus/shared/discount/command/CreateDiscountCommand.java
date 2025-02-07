package ir.cactus.shared.discount.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

public record CreateDiscountCommand(@TargetAggregateIdentifier Long id, String code , BigDecimal percentage,String expireDate,Long count) {}
