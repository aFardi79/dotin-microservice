package ir.cactus.controller;


import ir.cactus.model.Discount;
import ir.cactus.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/discount")
public class DiscountController {


    @Autowired
    private DiscountRepository discountRepository;



    @GetMapping("/getAllDiscounts")
    public ResponseEntity<List<Discount>>getAllDiscounts() {
        return new ResponseEntity<>(discountRepository.findAll(),new HttpHeaders(), HttpStatus.OK);
    }


    @GetMapping("/findDiscountById/{id}")
    public ResponseEntity<Discount>findDiscountById(@PathVariable("id") Long id) {
       Optional<Discount> discount = discountRepository.findById(id);
        return discount.map(value -> new ResponseEntity<>(value, new HttpHeaders(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/createDiscount")
    public void createDiscount(@RequestBody Discount discount) {
        discountRepository.save(discount);
    }

    @GetMapping("/findDiscountByCode/{code}")
    public ResponseEntity<Discount>findDiscountByCode(@PathVariable("code") String code) {
        return new ResponseEntity<>(discountRepository.findDiscountByCode(code),new HttpHeaders(), HttpStatus.OK);
    }

}
