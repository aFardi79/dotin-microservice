package ir.cactus.shared.notification.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateNotificationCommand(@TargetAggregateIdentifier Long id,String sender,String message,String entityName,String time,String entityId) {
}
