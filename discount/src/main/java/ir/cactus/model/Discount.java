package ir.cactus.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_discount")
public class Discount {

    private Long id;
    private BigDecimal percentage;
    private String code;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "c_percentage")
    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }


    @Column(name = "c_code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
