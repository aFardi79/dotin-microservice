package ir.cactus.model;

import java.math.BigDecimal;

public class Discount {
    private Long id;
    private BigDecimal percentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }
}
