package ir.cactus.discount;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DISCOUNT")
public interface DiscountClient {


    @GetMapping("/api/v1/discount/findDiscountByCode/{code}")
    DiscountDTO findDiscountByCode(@PathVariable("code") String code);

}
