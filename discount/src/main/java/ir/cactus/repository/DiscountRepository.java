package ir.cactus.repository;

import ir.cactus.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    Discount findDiscountByCode(String code);


}
