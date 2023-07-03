package org.example.infrastructure.repository;

import org.example.infrastructure.repository.entity.PriceDbo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceJpaRepository extends CrudRepository<Integer, PriceDbo> {


    @Query("SELECT p FROM PriceDbo p WHERE p.productId=:id AND p.brandId=:brandId AND p.startDate<=:date AND p.endDate>:date")
    List<PriceDbo> findActivePrices(int productId, int brandId, LocalDateTime dateTime);
}
