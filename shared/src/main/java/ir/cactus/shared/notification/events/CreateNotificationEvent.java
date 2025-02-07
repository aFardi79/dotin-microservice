package ir.cactus.shared.notification.events;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateNotificationEvent(Long id, String sender, String message, String entityName, String time, String entityId) {
}
