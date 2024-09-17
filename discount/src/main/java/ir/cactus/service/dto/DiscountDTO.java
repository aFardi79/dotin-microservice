package ir.cactus.service.dto;

import java.math.BigDecimal;

public class DiscountDTO {

    private BigDecimal percentage;
    private String code;
    private String expireDate;

    public BigDecimal getPercentage() {return percentage;}

    public void setPercentage(BigDecimal percentage) {this.percentage = percentage;}

    public String getCode() {return code;}

    public void setCode(String code) {this.code = code;}

    public String getExpireDate() {return expireDate;}

    public void setExpireDate(String expireDate) {this.expireDate = expireDate;}
}
