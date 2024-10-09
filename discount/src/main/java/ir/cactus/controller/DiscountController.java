package ir.cactus.controller;


import ir.cactus.service.IDiscountService;
import ir.cactus.service.dto.DiscountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discount")
@Slf4j
public class DiscountController {

    @Autowired
    private IDiscountService discountService;



    @GetMapping("/getAllDiscounts")
    public ResponseEntity<List<DiscountDTO>>getAllDiscounts() {
        return new ResponseEntity<>(discountService.findAll(),new HttpHeaders(), HttpStatus.OK);
    }


    /*@GetMapping("/findDiscountById/{id}")
    public ResponseEntity<Discount>findDiscountById(@PathVariable("id") Long id) {
       Optional<Discount> discount = discountRepository.findById(id);
        return discount.map(value -> new ResponseEntity<>(value, new HttpHeaders(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }*/

    @PostMapping("/createDiscount")
    public void createDiscount(@RequestBody DiscountDTO discountDTO) {
        discountService.create(discountDTO);
    }

    @PostMapping("/updateDiscount")
    public ResponseEntity<DiscountDTO> updateDiscount(@RequestBody DiscountDTO discountDTO) {
        return new ResponseEntity<>(discountService.update(discountDTO),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/findDiscountByCode/{code}")
    public DiscountDTO findDiscountByCode(@PathVariable("code") String code) {
        log.info("invoke getDiscount{}",code);
        throw new RuntimeException("errrorrr");
//        return discountService.findDiscountByCode(code);
    }

    @DeleteMapping("/deleteDiscountByCode/{code}")
    public void deleteDiscount(@PathVariable("code") String code){
        discountService.delete(code);
    }
}
