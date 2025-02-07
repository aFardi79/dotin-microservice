package ir.cactus.aggregate;



import ir.cactus.shared.notification.commands.CreateNotificationCommand;
import ir.cactus.shared.notification.events.CreateNotificationEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class NotificationAggregate {

    @AggregateIdentifier
    private Long Id;
    private String sender;
    private String message;
    private String EntityName;
    private String time;
    private String entityID;


    @CommandHandler
    public NotificationAggregate(CreateNotificationCommand command) {
        apply(new CreateNotificationEvent(command.id(), command.sender(), command.message(), command.entityName(), command.time(), command.entityId()));
    }

    @EventSourcingHandler
    public void on(CreateNotificationEvent event) {
        this.Id = event.id();
        this.sender = event.sender();
        this.message = event.message();
        this.EntityName = event.entityName();
        this.time = event.time();
        this.entityID = event.entityId();
    }

}
