package ir.cactus.controller;


import ir.cactus.model.Discount;
import ir.cactus.service.IDiscountService;
import ir.cactus.service.dto.DiscountDTO;
import ir.cactus.shared.discount.command.CreateDiscountCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@RestController
@RequestMapping("/api/v1/discount")
@Slf4j
public class DiscountController {

  private final IDiscountService discountService;
  private final CommandGateway commandGateway;



  public DiscountController(IDiscountService discountService, CommandGateway commandGateway) {
      this.discountService = discountService;
      this.commandGateway = commandGateway;
  }



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
    public String createDiscount(@RequestBody DiscountDTO discountDTO) {
        String disocuntCode=discountDTO.getCode()!=null?discountDTO.getCode(): UUID.randomUUID().toString();
        commandGateway.send(new CreateDiscountCommand(RandomGenerator.getDefault().nextLong(), disocuntCode,discountDTO.getPercentage(),discountDTO.getExpireDate(),discountDTO.getCount()));
        return "discount created";
    }

    @PostMapping("/updateDiscount")
    public ResponseEntity<DiscountDTO> updateDiscount(@RequestBody DiscountDTO discountDTO) {
        return new ResponseEntity<>(discountService.update(discountDTO),new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/findDiscountByCode/{code}")
    public DiscountDTO findDiscountByCode(@PathVariable("code") String code) {
        log.info("invoke getDiscount{}",code);
        return discountService.findDiscountByCode(code);
    }

    @DeleteMapping("/deleteDiscountByCode/{code}")
    public void deleteDiscount(@PathVariable("code") String code){
        discountService.delete(code);
    }

    record CreateDiscountRequest(String discountCode, int initialCount, double percentage) {}
}
