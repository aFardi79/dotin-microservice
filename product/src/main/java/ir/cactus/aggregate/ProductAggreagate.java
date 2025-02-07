package ir.cactus.aggregate;


import ir.cactus.shared.product.command.CreateProductCommand;
import ir.cactus.shared.product.events.CreateProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import java.math.BigDecimal;

@Deprecated
public class ProductAggreagate {

    @TargetAggregateIdentifier
    private Long id;
    private String name;
    private BigDecimal price;
    private String discountCODE;

    @CommandHandler
    public ProductAggreagate(CreateProductCommand command) {
        apply(new CreateProductEvent(command.id(),command.name(),command.price(),command.discountCode()));
    }


    @EventSourcingHandler
    public void on(CreateProductEvent event) {
        this.id = event.id();
        this.name = event.name();
        this.price = event.price();
        this.discountCODE = event.discountCode();
    }

}
