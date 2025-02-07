package ir.cactus.shared.product.events;



import java.math.BigDecimal;

public record CreateProductEvent(Long id, String name, BigDecimal price,String discountCode) {
}
