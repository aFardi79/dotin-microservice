package ir.cactus.aggregate;

import ir.cactus.shared.discount.command.CreateDiscountCommand;
import ir.cactus.shared.discount.command.ReleaseDiscountByCodeCommand;
import ir.cactus.shared.discount.command.ReserveDiscountCommand;
import ir.cactus.shared.discount.events.DiscountCreatedEvent;
import ir.cactus.shared.discount.events.DiscountReleaseEvent;
import ir.cactus.shared.discount.events.DiscountReservationFailedEvent;
import ir.cactus.shared.discount.events.DiscountReservedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class DiscountAggregate {
    @AggregateIdentifier
    private Long id;
    private BigDecimal percentage;
    private String code;
    private String expireDate;
    private Long count;

    protected DiscountAggregate() {}

    @CommandHandler
    public DiscountAggregate(CreateDiscountCommand command) {
        apply(new DiscountCreatedEvent(command.id(),command.code(),command.percentage(),command.expireDate(),command.count()));
    }

    @EventSourcingHandler
    public void on(DiscountCreatedEvent event) {
        this.code = event.code();
        this.percentage = event.percentage();
        this.expireDate= event.expiryDate();
    }

    @CommandHandler
    public void handel(ReserveDiscountCommand command) {
        if (command.count()>=0){
            apply(new DiscountReservationFailedEvent(code));
        }else{
            apply(new DiscountReservedEvent(code,percentage));
        }
    }

    @EventSourcingHandler
    public void on(DiscountReservedEvent event) {
        this.count-=1;
    }

    @CommandHandler
    public void handle(ReleaseDiscountByCodeCommand command) {
        apply(new DiscountReleaseEvent(code));
    }

    @EventSourcingHandler
    public void on(DiscountReleaseEvent event) {
        this.count+=1;
    }
}
