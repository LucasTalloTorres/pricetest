package org.example.infrastructure.repository;

import org.example.infrastructure.repository.entity.PriceDbo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceJpaRepository extends CrudRepository<PriceDbo, Integer> {

    @Query("SELECT p FROM PriceDbo p WHERE p.productId=:productId AND p.brandId=:brandId AND p.startDate<=:dateTime AND p.endDate>:dateTime")
    List<PriceDbo> findActivePrices(int productId, int brandId, LocalDateTime dateTime);

}
