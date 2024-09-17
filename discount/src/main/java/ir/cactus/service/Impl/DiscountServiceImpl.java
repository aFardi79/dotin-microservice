package ir.cactus.service.Impl;

import ir.cactus.model.Discount;
import ir.cactus.repository.DiscountRepository;
import ir.cactus.service.IDiscountService;
import ir.cactus.service.dto.DiscountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements IDiscountService {

    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public DiscountDTO findDiscountByCode(String code) {
        Discount discount = discountRepository.findDiscountByCode(code);
        return toDTO(discount);
    }

    @Override
    public List<DiscountDTO> findAll() {
        List<Discount> discounts = discountRepository.findAll();
        List<DiscountDTO> discountDTOs = new ArrayList<>();
        for (Discount discount : discounts) {
            discountDTOs.add(toDTO(discount));
        }
        return discountDTOs;
    }

    @Override
    public void delete(String code) {
        Discount discount = discountRepository.findDiscountByCode(code);
        discountRepository.delete(discount);
    }

    @Override
    public void create(DiscountDTO discountDTO) {
        discountRepository.save(toEntity(discountDTO));
    }

    @Override
    public DiscountDTO update(DiscountDTO discountDTO) {
        Discount discount = toEntity(discountDTO);
        discountRepository.save(discount);
        return toDTO(discount);
    }

    private DiscountDTO toDTO(Discount discount) {
        DiscountDTO discountDTO = new DiscountDTO();

        discountDTO.setCode(discount.getCode());
        discountDTO.setPercentage(discount.getPercentage());
        discountDTO.setExpireDate(discount.getExpireDate());

        return discountDTO;
    }

    private Discount toEntity(DiscountDTO discountDTO) {
        Discount discount = new Discount();

        discount.setCode(discountDTO.getCode());
        discount.setPercentage(discountDTO.getPercentage());
        discount.setExpireDate(discountDTO.getExpireDate());

        return discount;
    }
}
