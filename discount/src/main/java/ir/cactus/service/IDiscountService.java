package ir.cactus.service;

import ir.cactus.service.dto.DiscountDTO;

import java.util.List;


public interface IDiscountService  {
    DiscountDTO findDiscountByCode(String code);
    List<DiscountDTO> findAll();
    void delete(String code);

    void create (DiscountDTO discountDTO);

    DiscountDTO update (DiscountDTO discountDTO);

}
