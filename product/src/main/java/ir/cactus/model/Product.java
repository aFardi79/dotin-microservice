package ir.cactus.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Transient;
import java.math.BigDecimal;

@Data
@Entity
@Table(name="t_product")
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private String CouponCode;


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name="c_name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name="c_price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Transient
    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }


    public Product( String name, BigDecimal price, String couponCode) {
        this.name = name;
        this.price = price;
        CouponCode = couponCode;
    }
    public Product(){}
}
